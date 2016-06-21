package spring.desai.common.repository.impl.jpa;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@SuppressWarnings("unchecked")
public abstract class BaseJpaRepository<T extends Persistable> extends AbstractBaseRepository<T> {

	@PersistenceContext
	EntityManager entityManager;
	
	protected abstract Class<T> getEntityClass();
	
	@Override
	public T findById(String id) throws RepositoryDataAccessException {
		return entityManager.find(getEntityClass(), id);
	}
	
	@Override
	public void save(T persistable) throws RepositoryDataAccessException {
		entityManager.persist(persistable);
	}
	
	@Override
	public void update(T persistable) throws RepositoryDataAccessException {
		entityManager.merge(persistable);
	}
	
	@Override
	public Collection<T> findByName(String name) throws RepositoryDataAccessException {
		Query query = entityManager.createQuery(getFindBySql(getTableName(), getNameField()));
		query.setParameter(1, name);
		return (Collection<T>) query.getResultList();
	}
	
	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteByName(String name) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteAll() throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Collection<T> getAll() throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public int countAll() throws RepositoryDataAccessException {
		return (int) entityManager.createQuery(getTableName()).getSingleResult();
	}
	
	@Override
	protected final RowMapper<T> getRowMapper() {
		throwUnsupportedOperationException("getRowMapper()", this.getClass().getName());
		return null;
	}
	
	@Override
	protected final PreparedStatementSetter getInsertPreparedStatementSetter(T persistable) {
		throwUnsupportedOperationException("getInsertPreparedStatementSetter()", this.getClass().getName());
		return null;
	}
	
	@Override
	protected final BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(Collection<T> persistable) {
		throwUnsupportedOperationException("getInsertBatchPreparedStatementSetter()", this.getClass().getName());
		return null;
	}
	
	@Override
	protected final PreparedStatementSetter getUpdatePreparedStatementSetter(T persistable) {
		throwUnsupportedOperationException("getUpdatePreparedStatementSetter()", this.getClass().getName());
		return null;
	}
	
	@Override
	protected final BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(Collection<T> persistable) {
		throwUnsupportedOperationException("getUpdateBatchPreparedStatementSetter()", this.getClass().getName());
		return null;
	}
}
