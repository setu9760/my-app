package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface ScholorshipRepository extends BasePersistableRepository<Scholorship>{

	Collection<Scholorship> findByType(ScholorshipType type) throws RepositoryDataAccessException;
	
	Collection<Scholorship> findByStudentId(String stud_id) throws RepositoryDataAccessException;
}
