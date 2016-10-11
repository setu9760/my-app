package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.Subject;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 * Specific interface of {@link BasePersistableRepository} for type {@link Subject} 
 * @author desai
 */
public interface SubjectRepository extends BasePersistableRepository<Subject> {

	/**
	 * @param studentId
	 * @return returns collection of {@link Subject} for given studentId
	 * @throws RepositoryDataAccessException
	 */
	Collection<Subject> getSubjectsForStudentId(String studentId) throws RepositoryDataAccessException;
	
	/**
	 * @param costCode
	 * @return returns collection of {@link Subject} for given costCode
	 * @throws RepositoryDataAccessException
	 */
	Collection<Subject> findByCostCode(String costCode) throws RepositoryDataAccessException;

	/**
	 * @param isMandatory
	 * @return returns Collection of mandatory/non-mandatory Subjects
	 * @throws RepositoryDataAccessException
	 */
	Collection<Subject> findByMandatory(boolean isMandatory) throws RepositoryDataAccessException;

	/**
	 * Adds the given student to the subject
	 * @param studentId
	 * @param subject
	 * @throws RepositoryDataAccessException
	 */
	void addStudentToSubject(String studentId, Subject subject) throws RepositoryDataAccessException;
	
	/**
	 * Removes the given studet from the subject
	 * @param studentId
	 * @param subject
	 * @throws RepositoryDataAccessException
	 */
	void removeStudentFromSubject(String studentId, Subject subject) throws RepositoryDataAccessException;
	
	/**
	 * Check whether the student is in this subject.
	 * @param studentId
	 * @param subject
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	boolean isStudentInSubject(String studentId, Subject subject) throws RepositoryDataAccessException;
}
