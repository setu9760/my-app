package spring.desai.common.repository.impl.jpa;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import spring.desai.common.model.Cost;
import spring.desai.common.repository.CostCodeRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("costCodeRepository")
public class CostCodeRepositoryImplJpa extends BaseJpaRepository<Cost> implements CostCodeRepository{

	@Override
	public void saveAll(Collection<Cost> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAll(Collection<Cost> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	protected Class<Cost> getEntityClass() {
		return Cost.class;
	}

	@Override
	protected String getInsertSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getUpdateSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNameField() {
		// TODO Auto-generated method stub
		return null;
	}
}