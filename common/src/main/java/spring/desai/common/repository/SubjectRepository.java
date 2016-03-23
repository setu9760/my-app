package spring.desai.common.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Subject;

public interface SubjectRepository extends BasePersistableRepository {

	Subject findById(String id) throws DataAccessException;

	Subject findByName(String name) throws DataAccessException;

	Collection<Subject> getSubjectsForStudentId(String studentId) throws DataAccessException;
	
	Collection<Subject> findByCostCode(CostCode costCode) throws DataAccessException;

	Collection<Subject> findByMandatory(boolean isMandatory) throws DataAccessException;
	
	void deleteByName(String name) throws DataAccessException;
	
	int countAll() throws DataAccessException;

	Collection<Subject> getAll() throws RepositoryDataAccessException;

}
