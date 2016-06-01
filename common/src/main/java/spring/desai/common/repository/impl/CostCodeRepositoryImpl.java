package spring.desai.common.repository.impl;

import static spring.desai.common.utils.DataBaseConstants.COST_ABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.COST_CODE;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Cost;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.CostCodeRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("costCodeRepository")
public class CostCodeRepositoryImpl extends AbstractBaseRepository<Cost> implements CostCodeRepository {

	@Override
	public void saveImpl(Cost cost) throws RepositoryDataAccessException {
		try {
			checkPersitableValidity(cost);
			getJdbcTemplate().update(getInsertSql(), new Object[] {cost.getCostCode(), cost.getAmount()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void saveAllImpl(final Collection<Cost> persistables) throws RepositoryDataAccessException {
		try {
			final List<Cost> costs = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getInsertSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Cost c = costs.get(i);
					checkPersitableValidity(c);
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
	public void updateImpl(Cost cost) throws RepositoryDataAccessException {
		try {
			checkPersitableValidity(cost);
			getJdbcTemplate().update(getUpdateSql(), new Object[] {cost.getAmount(), cost.getCostCode()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void updateAllImpl(Collection<Cost> persistables) throws RepositoryDataAccessException {
		try {
			final List<Cost> costs = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getUpdateSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Cost c = costs.get(i);
					checkPersitableValidity(c);
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
	public Cost findByCode(CostCode costCode) throws RepositoryDataAccessException {
		return findById(costCode.toString());
	}
	
	@Override
	protected RowMapper<Cost> getRowMapper() {
		return getCostRowMapper();
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
	protected String getTableName() {
		return COST_ABLE_NAME;
	}
	
	@Override
	protected String getIdField() {
		return COST_CODE;
	}
	
	@Override
	protected String getNameField() {
		return null;
	}
}
