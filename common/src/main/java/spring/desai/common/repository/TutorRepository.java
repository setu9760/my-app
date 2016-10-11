package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.Tutor;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 * Specific interface of {@link BasePersistableRepository} for type {@link Tutor} 
 * @author desai
 */
public interface TutorRepository extends BasePersistableRepository<Tutor> {
	
	/**
	 * @param subj_id
	 * @return returns collections of Tutors for this given subjectId
	 * @throws RepositoryDataAccessException
	 */
	Collection<Tutor> getTutorsForSubject(String subjId) throws RepositoryDataAccessException;
}
