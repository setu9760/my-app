package spring.desai.common.repository;

import static spring.desai.common.utils.DataBaseConstants.ID;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;

import spring.desai.common.model.pojo.Cost;
import spring.desai.common.model.pojo.Payment;
import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.model.pojo.Student;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.I18N;

public abstract class AbstractBaseRepository<T extends Persistable> extends JdbcDaoSupport implements BasePersistableRepository<T> {

	private static final Logger logger = Logger.getLogger(AbstractBaseRepository.class);

	private static final Logger sqlLogger = Logger.getLogger("jdbcdaolog");

	@Autowired
	@Qualifier(value = "transactionManager")
	private PlatformTransactionManager platformTransactionManager;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Autowired
	@Qualifier("studentRowMapper")
	protected RowMapper<Student> studentRowMapper;

	@Autowired
	@Qualifier("subjectRowMapper")
	protected RowMapper<Subject> subjectRowMapper;

	@Autowired
	@Qualifier("tutorRowMapper")
	protected RowMapper<Tutor> tutorRowMapper;
	
	@Autowired
	@Qualifier("paymentRowMapper")
	protected RowMapper<Payment> paymentRowMapper;
	
	@Autowired
	@Qualifier("scholorshipRowMapper")
	protected RowMapper<Scholorship> scholorshipRowMapper;
	
	@Autowired
	@Qualifier("costRowMapper")
	protected RowMapper<Cost> costRowMapper;

	public static Logger getLogger() {
		return logger;
	}

	public static Logger getSqllogger() {
		return sqlLogger;
	}

	@PostConstruct
	public void init() {
		setDataSource(dataSource);
	}

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
	
	private final String truncateTableSql(String tableName) {
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
	
	private void checkPersitableValidity(T persistable){
		if (null == persistable) {
			throw new IllegalArgumentException(I18N.getString("error.null.object"));
		}
		if (null == persistable.getId() || persistable.getId().isEmpty()) {
			throw new IllegalArgumentException(I18N.getString("error.null.id"));
		}
	}
	
	private void checkPersitableValidity(Collection<T> coll){
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
		
	protected abstract String getInsertSql();
	protected abstract String getUpdateSql();
	protected abstract RowMapper<T> getRowMapper();
	protected abstract String getTableName();
	protected abstract PreparedStatementSetter getInsertPreparedStatementSetter(final T persistable);
	protected abstract PreparedStatementSetter getUpdatePreparedStatementSetter(final T persistable);
	protected abstract BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(final Collection<T> persistable);
	protected abstract BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(final Collection<T> persistable);
	
	protected String getIdField(){
		return ID;
	}
	protected abstract String getNameField();
	
	public void save(final T persistable) throws RepositoryDataAccessException {
		checkPersitableValidity(persistable);
		try {
			getJdbcTemplate().update(getInsertSql(), getInsertPreparedStatementSetter(persistable));
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	

	public void saveAll(final Collection<T> persistables) throws RepositoryDataAccessException {
		checkPersitableValidity(persistables);
		// TODO : Check the size of collection and break down call to saveAllImpl in smaller chunks to avoid large batch inserts.
		try {
			getJdbcTemplate().batchUpdate(getInsertSql(), getInsertBatchPreparedStatementSetter(persistables));
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public void update(T persistable) throws RepositoryDataAccessException {
		checkPersitableValidity(persistable);
		try {
			getJdbcTemplate().update(getUpdateSql(), getUpdatePreparedStatementSetter(persistable));
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public void updateAll(final Collection<T> persistables) throws RepositoryDataAccessException {
		checkPersitableValidity(persistables);
		// TODO : Check the size of collection and break down call to saveAllImpl in smaller chunks to avoid large batch inserts.
		try {
			getJdbcTemplate().batchUpdate(getUpdateSql(), getUpdateBatchPreparedStatementSetter(persistables));
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Deprecated
	public Collection<T> getAll() throws RepositoryDataAccessException{
		throwUnsupportedOperationException("getAll()", this.getClass().getName());
		return getJdbcTemplate().query(getSelectAllSql(getTableName()), getRowMapper());
	}

	public T findById(String id) throws RepositoryDataAccessException{
		try{
			List<T> l = getJdbcTemplate().query(getFindBySql(getTableName(), getIdField()), new Object[] { id }, getRowMapper());
			return (l != null && !l.isEmpty()) ? l.get(0) : null;
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public Collection<T> findByName(String name) throws RepositoryDataAccessException{
		try{
			return getJdbcTemplate().query(getFindBySql(getTableName(), getNameField()), new Object[] { "%" + name + "%" }, getRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public void deleteById(String id) throws RepositoryDataAccessException{
		try {
			getJdbcTemplate().update(getDeleteBySql(getTableName(), getIdField()), new Object[]{ id });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public void deleteByName(String name) throws RepositoryDataAccessException{
		throwUnsupportedOperationException("deleteByName()", this.getClass().getName());
		try {
			getJdbcTemplate().update(getDeleteBySql(getTableName(), getNameField()), new Object[] { name });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public void deleteAll() throws RepositoryDataAccessException{
		throwUnsupportedOperationException("deleteAll()", this.getClass().getName());
	}
	
	public int countAll() throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().queryForObject(getCountAllSql(getTableName()), Integer.class);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
}
