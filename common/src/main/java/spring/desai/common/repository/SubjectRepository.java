package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.pojo.Subject;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface SubjectRepository extends BasePersistableRepository<Subject> {

	Collection<Subject> getSubjectsForStudentId(String studentId) throws RepositoryDataAccessException;
	
	Collection<Subject> findByCostCode(String costCode) throws RepositoryDataAccessException;

	Collection<Subject> findByMandatory(boolean isMandatory) throws RepositoryDataAccessException;

}
