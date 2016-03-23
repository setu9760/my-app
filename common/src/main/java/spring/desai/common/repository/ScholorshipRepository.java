package spring.desai.common.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.model.pojo.Scholorship.ScholorshipType;

public interface ScholorshipRepository extends BasePersistableRepository{

	Scholorship findById(String id) throws DataAccessException;

	Collection<Scholorship> findByType(ScholorshipType type) throws DataAccessException;
}
