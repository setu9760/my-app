package spring.desai.common.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import spring.desai.common.model.pojo.Tutor;

public interface TutorRepository extends BasePersistableRepository {

	Tutor findById(String id) throws DataAccessException;

	Collection<Tutor> findbyName(String name) throws DataAccessException;

	void deleteByName(String name) throws DataAccessException;
	
	int countAll() throws DataAccessException;
	
	Collection<Tutor> getAll() throws DataAccessException;

	Collection<Tutor> getTutorsForSubject(String subj_id) throws DataAccessException;
}
