package spring.desai.common.repository;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Cost;

public interface CostCodeRepository extends BasePersistableRepository {

	Cost findByCode(CostCode costCode) throws RepositoryDataAccessException;
	
}
