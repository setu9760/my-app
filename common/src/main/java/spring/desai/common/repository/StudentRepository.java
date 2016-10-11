package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.Student;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 * Specific interface of {@link BasePersistableRepository} for type {@link Student}
 * @author desai
 */
public interface StudentRepository extends BasePersistableRepository<Student>{

	/**
	 * 
	 * @param subj_id
	 * @return Collection of students for the given subjectId
	 * @throws RepositoryDataAccessException
	 */
	Collection<Student> getStudentsForSubjectId(String subjId) throws RepositoryDataAccessException;
}
