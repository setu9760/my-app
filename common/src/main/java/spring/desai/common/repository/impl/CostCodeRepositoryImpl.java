package spring.desai.common.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Cost;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.CostCodeRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.DataBaseConstants;

@Repository("costCodeRepository")
public class CostCodeRepositoryImpl extends AbstractBaseRepository implements CostCodeRepository {

	@Override
	public void save(Cost cost) throws RepositoryDataAccessException {
		try {
			getJdbcTemplate().update(getInsertSql(), new Object[] {cost.getCostCode(), cost.getAmount()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void saveAll(final Collection<Cost> persistables) throws RepositoryDataAccessException {
		try {
			final List<Cost> costs = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getInsertSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Cost c = costs.get(i);
					ps.setString(1, c.getCostCode());
					ps.setDouble(2, c.getAmount());
				}
				
				@Override
				public int getBatchSize() {
					return costs.size();
				}
			});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void update(Cost cost) throws RepositoryDataAccessException {
		try {
			getJdbcTemplate().update(getUpdateSql(), new Object[] {cost.getAmount(), cost.getCostCode()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void updateAll(Collection<Cost> persistables) throws RepositoryDataAccessException {
		try {
			final List<Cost> costs = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getUpdateSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Cost c = costs.get(i);
					ps.setDouble(1, c.getAmount());
					ps.setString(2, c.getCostCode());
				}
				
				@Override
				public int getBatchSize() {
					return costs.size();
				}
			});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Cost findById(String id) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.COST_ABLE_NAME, DataBaseConstants.COST_CODE),  new Object[] { id }, getCostRowMapper()).get(0);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Cost> findByName(String name) throws RepositoryDataAccessException {
		throw new UnsupportedOperationException("findByName is not supported for CostCodeRepository");
	}

	@Override
	public Cost findByCode(CostCode costCode) throws RepositoryDataAccessException {
		return findById(costCode.toString());
	}
	
	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.COST_ABLE_NAME, DataBaseConstants.COST_CODE), id);
	}

	@Override
	public void deleteAll() throws RepositoryDataAccessException {
		deleteAllImpl(DataBaseConstants.COST_ABLE_NAME);
	}

	@Override
	public int countAll() throws RepositoryDataAccessException {
		return countAllImpl(getCountAllSql(DataBaseConstants.COST_ABLE_NAME));
	}

	@Override
	protected String getInsertSql() {
		return "INSERT INTO cost VALUES (?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE COST SET amount = ? WHERE cost_code = ?";
	}

	@Override
	public Collection<Cost> getAll() throws RepositoryDataAccessException {
		return (Collection<Cost>) getAllImpl(getSelectAllSql(DataBaseConstants.COST_ABLE_NAME), Cost.class);
	}
}
