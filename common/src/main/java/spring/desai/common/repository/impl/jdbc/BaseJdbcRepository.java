package spring.desai.common.repository.impl.jdbc;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import spring.desai.common.model.pojo.Cost;
import spring.desai.common.model.pojo.Payment;
import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.model.pojo.Student;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.rowmappers.UserRowMapper;

public abstract class BaseJdbcRepository<T extends Persistable> extends AbstractBaseRepository<T> {

	private static final Logger logger = Logger.getLogger(BaseJdbcRepository.class);

	private static final Logger sqlLogger = Logger.getLogger("jdbcdaolog");

	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager platformTransactionManager;

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
	@Qualifier("scholorshipRowMapper")
	protected RowMapper<Scholorship> scholorshipRowMapper;
	
	@Autowired
	protected UserRowMapper userRowMapper;
	
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
		try {
			getJdbcTemplate().update(getInsertSql(), getInsertPreparedStatementSetter(persistable));
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	

	public void saveAll(final Collection<T> persistables) throws RepositoryDataAccessException {
		checkPersitableValidity(persistables);
//		List<Collection<T>> l = makeChunksFromCollections(persistables);
//		logger.info("Performing saveAll for " + persistables.size() + " persistables in " + l.size() + " batches.");
//		for (Collection<T> collection : l) {
			try {
				getJdbcTemplate().batchUpdate(getInsertSql(), getInsertBatchPreparedStatementSetter(persistables));
			} catch (DataAccessException e) {
				throw new RepositoryDataAccessException(e);
			}
//		}
	}
	
//	private List<Collection<T>> makeChunksFromCollections(final Collection<T> persistables){
//		int size = persistables.size();
//		int numOfChunks;
//		List<Collection<T>> result;
//		if (size <= 50) {
//			numOfChunks = 1;
//			result = new ArrayList<>(numOfChunks);
//			result.add(persistables);
//			return result;
//		} if (size > 50 && size <= 100) {
//			numOfChunks = 2;
//		} else if (size > 100 && size <= 250) {
//			numOfChunks = 3;
//		} else if (size > 250 && size <= 500) {
//			numOfChunks = 4;
//		} else if (size > 500 && size <= 1500) {
//			numOfChunks = 5;
//		} else {
//			numOfChunks = 10;
//		}
//		Iterator<T> persistableIterator = persistables.iterator();
//		result = new ArrayList<>(numOfChunks);
//		int counter = 0;
//		while (persistableIterator.hasNext()) {
//			result.get(counter).add(persistableIterator.next());
//			if (counter >= numOfChunks) {
//				counter = 0;
//				break;
//			}
//			counter++;
//		}
//		return result;
//	}
	
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
