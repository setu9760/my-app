package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.Scholarship;
import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 * Specific interface of {@link BasePersistableRepository} for type {@link Scholarship} 
 * @author desai
 */
public interface ScholarshipRepository extends BasePersistableRepository<Scholarship>{

	/**
	 *  Find all the scholarships from {@link ScholorshipType}
	 * @param type
	 * @return collection of {@link Scholarship}
	 * @throws RepositoryDataAccessException
	 */
	Collection<Scholarship> findByType(ScholorshipType type) throws RepositoryDataAccessException;
	
	/**
	 * Find all the scholarships given to this studeId
	 * @param stud_id
	 * @return collection of {@link Scholarship}
	 * @throws RepositoryDataAccessException
	 */
	Collection<Scholarship> findByStudentId(String studId) throws RepositoryDataAccessException;
}
