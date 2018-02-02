package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.Persistable;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 * Base repository which provides CRUD operations to persistent store. The interfaces should avoide handling 
 * transactionality at this level as much possible. The transactions are handled at the service level by application.
 * 
 * <p>
 * The validity of input persistables should be handled by implementations as requirements. and any checked exception should
 * be wrapped in  {@link RepositoryDataAccessException} before throwing to manage the exception hierarchy supported by service
 * layer.
 * @author desai
 *
 * @param <T> The type of the persistable object 
 */
public interface BasePersistableRepository <T extends Persistable>{

	/**
	 * Performs save operation for the persistable
	 * @param persistable persistent object to be saved.
	 * @throws RepositoryDataAccessException
	 */
	void save(T persistable) throws RepositoryDataAccessException;

	/**
	 * Performs save operation for the collection of persistable.
	 * @param persistables collection of persistent objjects to be saved.
	 * @throws RepositoryDataAccessException
	 */
	void saveAll(final Collection<T> persistables) throws RepositoryDataAccessException;
	
	/**
	 * Performs update operation for the persistable. Each persistable has unique id field which should be used
	 * to lookup the object for updation.
	 * @param persistable
	 * @throws RepositoryDataAccessException
	 */
	void update(T persistable) throws RepositoryDataAccessException;
	
	/**
	 * Performs update operation for the collection of persistables.
	 * @param persistables
	 * @throws RepositoryDataAccessException
	 */
	void updateAll(final Collection<T> persistables) throws RepositoryDataAccessException;
	
	/**
	 * Returns all persistable for the repository type.
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	Collection<T> getAll() throws RepositoryDataAccessException;
	
	/**
	 * Looksup persistable based of the id and returns the object. If no object found returns null.
	 * @param id
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	T findById(String id) throws RepositoryDataAccessException;
	
	/**
	 * Looksup persistables based of the name and returns the collection of objects. If no object found returns empty collection.
	 * @param name
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	Collection<T> findByName(String name) throws RepositoryDataAccessException;
	
	/**
	 * Performs delete operation based on persistable id field. 
	 * @param id
	 * @throws RepositoryDataAccessException
	 */
	void delete(String id) throws RepositoryDataAccessException;
	
	/**
	 * Performs delete operations based on persistable name field.
	 * @param name
	 * @throws RepositoryDataAccessException
	 */
	void deleteByName(String name) throws RepositoryDataAccessException;
	
	/**
	 * Deletes all persistables for this repository.
	 * 
	 * <p>
	 * <b>Should be used carefully. If possible should just thrown {@link UnsupportedOperationException}.</b>
	 * @throws RepositoryDataAccessException
	 */
	void deleteAll() throws RepositoryDataAccessException;
	
	/**
	 * Returns count of number of persistables for this repository.
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	int countAll() throws RepositoryDataAccessException;
}
