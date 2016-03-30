package spring.desai.common.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.model.pojo.Student;

public interface StudentRepository extends BasePersistableRepository{

	Student findById(String id) throws DataAccessException;

	Collection<Student> findbyName(String name) throws RepositoryDataAccessException;

	void save(Persistable student) throws RepositoryDataAccessException;

	void update(Persistable student) throws RepositoryDataAccessException;
	
	void deleteByName(String name) throws RepositoryDataAccessException;
	
	int countAll() throws RepositoryDataAccessException;

	Collection<Student> getAll() throws RepositoryDataAccessException;
	
	Collection<Student> getStudentsForSubjectId(String subj_id) throws RepositoryDataAccessException;
}
