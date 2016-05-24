package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface BasePersistableRepository <T extends Persistable>{

	void save(T persistable) throws RepositoryDataAccessException;

	void saveAll(final Collection<T> persistables) throws RepositoryDataAccessException;
	
	void update(T persistable) throws RepositoryDataAccessException;
	
	void updateAll(final Collection<T> persistables) throws RepositoryDataAccessException;
	
	@Deprecated
	Collection<T> getAll() throws RepositoryDataAccessException;
	
	T findById(String id) throws RepositoryDataAccessException;
	
	Collection<T> findByName(String name) throws RepositoryDataAccessException;
	
	void deleteById(String id) throws RepositoryDataAccessException;
	
	void deleteAll() throws RepositoryDataAccessException;
	
	int countAll() throws RepositoryDataAccessException;
}
