package spring.desai.common.repository;

import static spring.desai.common.utils.DataBaseConstants.ID;

import java.util.Collection;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.Persistable;
import spring.desai.common.utils.I18N;

public abstract class AbstractBaseRepository<T extends Persistable> implements BasePersistableRepository<T>  {

	protected final String getSelectAllSql(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName);
		return sb.toString();
	}
	
	protected final String getFindBySql(String tableName, String fieldName) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName).append(" WHERE").append(" LOWER (").append(fieldName).append(") LIKE LOWER (?)");
		return sb.toString();
	}
	
	protected final String getFindBySqlWhereIn(String tableName, String fieldName, int length) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName).append(" WHERE ").append(fieldName).append(" in (");
		for (int i = 0; i < length; i++) {
			sb.append(" ?");
			if (i != (length-1)) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	protected final String getFieldFindBySql(String tableName, String findByFieldName, String fieldToFind){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ").append(fieldToFind).append(" FROM ").append(tableName).append(" WHERE ").append(findByFieldName).append(" = ?");
		return sb.toString();
	}
	
	protected final String getCountAllSql(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*) FROM ").append(tableName);
		return sb.toString();
	}
	
	protected final String getDeleteBySql(String tableName, String fieldName) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ").append(tableName).append(" WHERE ").append(fieldName).append(" = ?");
		return sb.toString();
	}
	
	protected final String truncateTableSql(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("TRUNCATE TABLE ").append(tableName);
		return sb.toString();
	}
	
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
				sb.append(",");
			}
		}
		sb.append(" WHERE id = ?");
		return sb.toString();
	}
	
	protected void checkPersitableValidity(T persistable){
		if (null == persistable) {
			throw new IllegalArgumentException(I18N.getString("error.null.object"));
		}
		if (null == persistable.getId() || persistable.getId().isEmpty()) {
			throw new IllegalArgumentException(I18N.getString("error.null.id"));
		}
	}
	
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
	
	protected void throwUnsupportedOperationException(String operation, String forClass){
		throw new UnsupportedOperationException(I18N.getString("error.unsupported.op", new Object[] {operation, forClass}));
	}
	
	protected String getIdField(){
		return ID;
	}
	protected abstract String getInsertSql();
	protected abstract String getUpdateSql();
	protected abstract RowMapper<T> getRowMapper();
	protected abstract String getTableName();
	protected abstract String getNameField();
	protected abstract PreparedStatementSetter getInsertPreparedStatementSetter(final T persistable);
	protected abstract PreparedStatementSetter getUpdatePreparedStatementSetter(final T persistable);
	protected abstract BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(final Collection<T> persistable);
	protected abstract BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(final Collection<T> persistable);
	
}
