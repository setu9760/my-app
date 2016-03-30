package spring.desai.common.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.model.pojo.Scholorship;

public interface ScholorshipRepository extends BasePersistableRepository{

	Scholorship findById(String id) throws DataAccessException;

	Collection<Scholorship> findByType(ScholorshipType type) throws DataAccessException;
	
	Collection<Scholorship> findByStudent(String stud_id) throws DataAccessException;
}
