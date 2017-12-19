package spring.desai.common.repository.impl.jdbc;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.Cost;
import spring.desai.common.model.Payment;
import spring.desai.common.model.Persistable;
import spring.desai.common.model.Scholarship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.rowmappers.RolesRowMapper;
import spring.desai.common.rowmappers.UserRowMapper;

/**
 * Abstract implementation of {@link BasePersistableRepository} for jdbc persistent store. This class provides
 * implementation of most generic CRUD operations for conivinience which could be overridden by any child implementaions
 * to provide custom functionality. 
 * 
 * <p>All {@link DataAccessException} encountered during the jdbc operations are caught and wrapped in {@link RepositoryDataAccessException}
 * before being thrown and the same should be done for any class extending this repository.  
 * @author desai
 *
 * @param <T> The type of persistable
 */
public abstract class BaseJdbcRepository<T extends Persistable> extends AbstractBaseRepository<T> {

	private static final Logger logger = Logger.getLogger(BaseJdbcRepository.class);

	private static final Logger sqlLogger = Logger.getLogger("jdbcdaolog");

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
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
	@Qualifier("scholarshipRowMapper")
	protected RowMapper<Scholarship> scholarshipRowMapper;
	
	@Autowired
	@Qualifier("userRowMapper")
	protected UserRowMapper userRowMapper;
	
	@Autowired
	@Qualifier("rolesRowMapper")
	protected RolesRowMapper rolesRowMapper;
	
	@Autowired
	@Qualifier("costRowMapper")
	protected RowMapper<Cost> costRowMapper;

	public static Logger getLogger() {
		return logger;
	}

	public static Logger getSqllogger() {
		return sqlLogger;
	}
	
	public void save(final T persistable) throws RepositoryDataAccessException {
		checkPersitableValidity(persistable);
		getJdbcTemplate().update(getInsertSql(), getInsertPreparedStatementSetter(persistable));
	}
	
	public void saveAll(final Collection<T> persistables) throws RepositoryDataAccessException {
		checkPersitableValidity(persistables);
		// TODO : Check the size of collection and break down call in smaller chunks to avoid large batch inserts/updates.
		getJdbcTemplate().batchUpdate(getInsertSql(), getInsertBatchPreparedStatementSetter(persistables));
	}
	
	public void update(T persistable) throws RepositoryDataAccessException {
		checkPersitableValidity(persistable);
		getJdbcTemplate().update(getUpdateSql(), getUpdatePreparedStatementSetter(persistable));
	}
	
	public void updateAll(final Collection<T> persistables) throws RepositoryDataAccessException {
		checkPersitableValidity(persistables);
		// TODO : Check the size of collection and break down call in smaller chunks to avoid large batch inserts/updates.
		getJdbcTemplate().batchUpdate(getUpdateSql(), getUpdateBatchPreparedStatementSetter(persistables));
	}
	
//	@Deprecated
	public Collection<T> getAll() throws RepositoryDataAccessException {
//		throwUnsupportedOperationException("getAll()", this.getClass().getName());
		return getJdbcTemplate().query(getSelectAllSql(getTableName()), getRowMapper());
	}

	public T findById(String id) throws RepositoryDataAccessException {
		List<T> l = getJdbcTemplate().query(getFindBySql(getTableName(), getIdField()), new Object[] { id }, getRowMapper());
		return (l != null && !l.isEmpty()) ? l.get(0) : null;
	}
	
	public Collection<T> findByName(String name) throws RepositoryDataAccessException {
		return getJdbcTemplate().query(getFindBySql(getTableName(), getNameField()), new Object[] { "%" + name + "%" }, getRowMapper());
	}
	
	public void deleteById(String id) throws RepositoryDataAccessException {
		getJdbcTemplate().update(getDeleteBySql(getTableName(), getIdField()), new Object[]{ id });
	}
	
	public void deleteByName(String name) throws RepositoryDataAccessException {
		if (!isDeleteByNameOpSupported()) {
			throwUnsupportedOperationException("deleteByName()", this.getClass().getName());
		}
		getJdbcTemplate().update(getDeleteBySql(getTableName(), getNameField()), new Object[] { name });
	}
	
	public void deleteAll() throws RepositoryDataAccessException {
		if (!isDeleteAllOpSupported()) {
			throwUnsupportedOperationException("deleteAll()", this.getClass().getName());
		}
	}
	
	public int countAll() throws RepositoryDataAccessException {
		return getJdbcTemplate().queryForObject(getCountAllSql(getTableName()), Integer.class);
	}
}
