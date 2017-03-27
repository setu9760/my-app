package spring.desai.common.repository;

import static spring.desai.common.utils.DataBaseConstants.ID;

import java.util.Collection;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.Persistable;
import spring.desai.common.utils.I18N;

/**
 * Abstract class to provide convenient methods for {@link BasePersistableRepository} implementations where the persistent store is jdbc sql based. 
 * 
 * @author desai
 *
 * @param <T> The type of the persistable object
 */
public abstract class AbstractBaseRepository<T extends Persistable> implements BasePersistableRepository<T>  {

	/**
	 * Retruns sql to perform selectAll operation on the table name supplied. 
	 * @param tableName
	 * @return
	 */
	protected final String getSelectAllSql(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName);
		return sb.toString();
	}
	
	/**
	 * Returns the sql to perform findBy operation based on the tableName and fieldName fields. 
	 * @param tableName
	 * @param fieldName
	 * @return
	 */
	protected final String getFindBySql(String tableName, String fieldName) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName).append(" WHERE").append(" LOWER (").append(fieldName).append(") LIKE LOWER (?)");
		return sb.toString();
	}
	
	/**
	 * Returns the sql to perform findBy operation where the findBy field has more than one value. 
	 * @param tableName
	 * @param fieldName
	 * @param length
	 * @return
	 */
	protected final String getFindBySqlWhereIn(String tableName, String fieldName, int length) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName).append(" WHERE ").append(fieldName).append(" in (");
		for (int i = 0; i < length; i++) {
			sb.append(" ?");
			if (i != (length-1)) {
				sb.append(',');
			}
		}
		sb.append(')');
		return sb.toString();
	}
	
	/**
	 * Return sql to perform findBy operation but only return the one required field rather than complete row from the table.
	 * @param tableName
	 * @param findByFieldName
	 * @param fieldToFind
	 * @return
	 */
	protected final String getFieldFindBySql(String tableName, String findByFieldName, String fieldToFind){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ").append(fieldToFind).append(" FROM ").append(tableName).append(" WHERE ").append(findByFieldName).append(" = ?");
		return sb.toString();
	}
	
	/**
	 * return count all sql for given table name.
	 * @param tableName
	 * @return
	 */
	protected final String getCountAllSql(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*) FROM ").append(tableName);
		return sb.toString();
	}
	
	/**
	 * return delete by field sql for given tableName
	 * @param tableName
	 * @param fieldName
	 * @return
	 */
	protected final String getDeleteBySql(String tableName, String fieldName) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ").append(tableName).append(" WHERE ").append(fieldName).append(" = ?");
		return sb.toString();
	}
	
	/**
	 * return truncate table sql for given tableName
	 * @param tableName
	 * @return
	 */
	protected final String truncateTableSql(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("TRUNCATE TABLE ").append(tableName);
		return sb.toString();
	}
	
	/**
	 * return update sql for tableName and the field to inlcude in update set.
	 * @param tableName
	 * @param fields
	 * @return
	 */
	protected final String getUpdateSqlForFields(String tableName, String... fields){
		if (fields == null || fields.length == 0) {
			return getUpdateSql();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(tableName);
		sb.append(" SET ");
		for (int i = 0; i < fields.length; i++) {
			sb.append(fields[i]);
			sb.append(" = ? ");
			if (i < fields.length-1) {
				sb.append(',');
			}
		}
		sb.append(" WHERE id = ?");
		return sb.toString();
	}
	
	/**
	 * validates the persistable object by performing null check on the object itself aswell persistable.getId().
	 * @param persistable
	 */
	protected void checkPersitableValidity(T persistable){
		if (null == persistable) {
			throw new IllegalArgumentException(I18N.getString("error.null.object"));
		}
		if (null == persistable.getId() || persistable.getId().isEmpty()) {
			throw new IllegalArgumentException(I18N.getString("error.null.id"));
		}
	}
	
	/**
	 * validates the collection of persistable by performing null check on the collection as well as each persistable 
	 * and their id.
	 * @param coll
	 */
	protected void checkPersitableValidity(Collection<T> coll){
		if (null == coll || coll.isEmpty()) {
			throw new IllegalArgumentException(I18N.getString("error.null.object"));
		}
		for (Persistable persistable : coll) {
			if (null == persistable.getId() || persistable.getId().isEmpty()) {
				throw new IllegalArgumentException(I18N.getString("error.null.id"));
			}
		}
	}
	
	/**
	 * Throws {@link UnsupportedOperationException} for with custom error message for given method and class.
	 * @param operation
	 * @param forClass
	 */
	protected void throwUnsupportedOperationException(String operation, String forClass){
		throw new UnsupportedOperationException(I18N.getString("error.unsupported.op", new Object[] {operation, forClass}));
	}
	
	/**
	 * Return default id field in the persistent store for the repository. 
	 * @return
	 */
	protected String getIdField(){
		return ID;
	}
	
	/**
	 * return flag if deleteByName operations be supported for this repository.
	 * @return 
	 */
	protected boolean isDeleteByNameOpSupported() {
		return false;
	}
	
	/**
	 * return flag if deleteAll operation be supported for this repository.
	 * @return
	 */
	protected boolean isDeleteAllOpSupported() {
		return false;
	}
	
	/**
	 * @return Returns the insert sql based on table name and number of fields for this repository.
	 */
	protected abstract String getInsertSql();

	/**
	 * @return Returns the update sql based on table name and number of fields for this repository.
	 */
	protected abstract String getUpdateSql();
	
	/**
	 * @return Rowmapper to be used by spring jdbcTemplate 
	 */
	protected abstract RowMapper<T> getRowMapper();
	
	/**
	 * @return The table name associated with this repository
	 */
	protected abstract String getTableName();
	
	/**
	 * @return The name field associated with this repository table.
	 */
	protected abstract String getNameField();
	
	/**
	 * @param persistable
	 * @return Complete PreparedStatemntSetter for insert operation for the persistable.
	 */
	protected abstract PreparedStatementSetter getInsertPreparedStatementSetter(final T persistable);
	
	/**
	 * @param persistable
	 * @return Complete PreparedStatementSetter for update operation for the persistable
	 */
	protected abstract PreparedStatementSetter getUpdatePreparedStatementSetter(final T persistable);
	
	/**
	 * @param persistable
	 * @return Complete BatchPreparedStatementSetter for insert operation for the batch of persistables.
	 */
	protected abstract BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(final Collection<T> persistable);
	
	/**
	 * @param persistable
	 * @return Complete BatchPreparedStatementSetter for update operation for the batch of persistables.
	 */
	protected abstract BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(final Collection<T> persistable);
	
}
