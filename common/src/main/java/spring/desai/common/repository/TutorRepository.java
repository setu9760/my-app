package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface TutorRepository extends BasePersistableRepository<Tutor> {

	void deleteByName(String name) throws RepositoryDataAccessException;
	
	Collection<Tutor> getTutorsForSubject(String subj_id) throws RepositoryDataAccessException;
}
