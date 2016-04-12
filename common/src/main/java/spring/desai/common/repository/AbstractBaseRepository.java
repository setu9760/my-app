package spring.desai.common.repository;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
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

public abstract class AbstractBaseRepository extends JdbcDaoSupport {

	private static final Logger logger = Logger.getLogger(AbstractBaseRepository.class);

	private static final Logger sqlLogger = Logger.getLogger("jdbcdaolog");

	@Autowired
	@Qualifier(value = "transactionManager")
	protected PlatformTransactionManager platformTransactionManager;

	@Autowired
	protected DataSource dataSource;

	@Autowired
	@Qualifier("studentRowMapper")
	private RowMapper<Student> studentRowMapper;

	@Autowired
	@Qualifier("subjectRowMapper")
	private RowMapper<Subject> subjectRowMapper;

	@Autowired
	@Qualifier("tutorRowMapper")
	private RowMapper<Tutor> tutorRowMapper;
	
	@Autowired
	@Qualifier("paymentRowMapper")
	private RowMapper<Payment> paymentRowMapper;
	
	@Autowired
	@Qualifier("scholorshipRowMapper")
	private RowMapper<Scholorship> scholorshipRowMapper;
	
	@Autowired
	@Qualifier("costRowMapper")
	private RowMapper<Cost> costRowMapper;

	public RowMapper<Student> getStudentRowMapper() {
		return studentRowMapper;
	}

	public RowMapper<Subject> getSubjectRowMapper() {
		return subjectRowMapper;
	}

	public RowMapper<Tutor> getTutorRowMapper() {
		return tutorRowMapper;
	}
	
	public RowMapper<Payment> getPaymentRowMapper() {
		return paymentRowMapper;
	}
	
	public RowMapper<Scholorship> getScholorshipRowMapper() {
		return scholorshipRowMapper;
	}
	
	public RowMapper<Cost> getCostRowMapper() {
		return costRowMapper;
	}

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
		sb.append("SELECT * FROM ").append(tableName).append(" WHERE ").append(fieldName).append(" = ?");
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
	
	protected final Collection<? extends Persistable> getAllImpl(String sql, Class<? extends Persistable> classType){
		try {
			return getJdbcTemplate().query(sql, getRowMapperForClass(classType));
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	protected final void deleteImpl(String sql, String fieldValue) throws RepositoryDataAccessException{
		try {
			getJdbcTemplate().update(sql, new Object[] {fieldValue}); 
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	protected final int countAllImpl(String sql) throws RepositoryDataAccessException{
		try {
			return getJdbcTemplate().queryForObject(sql, Integer.class);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	private RowMapper<? extends Persistable> getRowMapperForClass(Class<?> classType) {

		if (classType.getName().contains("Student")) {
			return getStudentRowMapper();
		} else if (classType.getName().contains("Subject")){
			return getSubjectRowMapper();
		} else if (classType.getName().contains("Tutor")){
			return getTutorRowMapper();
		} else if (classType.getName().contains("Payment")){
			return getPaymentRowMapper();
		} else if (classType.getName().contains("Scholorship")){
			return getScholorshipRowMapper();
		} else {
			throw new IllegalArgumentException("Rowmapper for classtype " + classType.getName() + " is unsupported.");
		}
	}
	
	protected void checkNotNull(Persistable persistable){
		if (persistable == null) {
			throw new IllegalArgumentException("Null Argument passed.");
		}
	}
	protected abstract String getInsertSql();
	protected abstract String getUpdateSql();
}
