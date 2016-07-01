package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.Subject;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface SubjectRepository extends BasePersistableRepository<Subject> {

	Collection<Subject> getSubjectsForStudentId(String studentId) throws RepositoryDataAccessException;
	
	Collection<Subject> findByCostCode(String costCode) throws RepositoryDataAccessException;

	Collection<Subject> findByMandatory(boolean isMandatory) throws RepositoryDataAccessException;

	void addStudentToSubject(String studentId, Subject subject) throws RepositoryDataAccessException;
	
	void removeStudentFromSubject(String studentId, Subject subject) throws RepositoryDataAccessException;
	
	boolean isStudentInSubject(String studentId, Subject subject) throws RepositoryDataAccessException;
}
