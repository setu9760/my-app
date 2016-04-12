package spring.desai.common.repository;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Cost;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface CostCodeRepository extends BasePersistableRepository<Cost> {

	Cost findByCode(CostCode costCode) throws RepositoryDataAccessException;
	
}
