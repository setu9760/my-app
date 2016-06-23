package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.Student;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface StudentRepository extends BasePersistableRepository<Student>{

	void deleteByName(String name) throws RepositoryDataAccessException;
	
	Collection<Student> getStudentsForSubjectId(String subj_id) throws RepositoryDataAccessException;
}
