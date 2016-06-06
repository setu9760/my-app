package spring.desai.common.repository.impl.jpa;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@SuppressWarnings("unchecked")
public abstract class BaseJpaRepository<T extends Persistable> extends AbstractBaseRepository<T> {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public T findById(String id) throws RepositoryDataAccessException {
		Query query = entityManager.createQuery(getFindBySql(getTableName(), getIdField()));
		query.setParameter(1, id);
		return (T) query.getSingleResult();
	}
	
	@Override
	public void save(T persistable) throws RepositoryDataAccessException {
		entityManager.persist(persistable);
	}
	
	@Override
	public Collection<T> findByName(String name) throws RepositoryDataAccessException {
		Query query = entityManager.createQuery(getFindBySql(getTableName(), getNameField()));
		query.setParameter(1, name);
		return (Collection<T>) query.getResultList();
	}
	
}
