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
import org.springframework.jdbc.core.PreparedStatementSetter;
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
	public Cost findByCode(CostCode costCode) throws RepositoryDataAccessException {
		return findById(costCode.toString());
	}
	
	@Override
	protected RowMapper<Cost> getRowMapper() {
		return costRowMapper;
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
	
	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final Cost c) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, c.getId());
				ps.setDouble(2, c.getAmount());
			}
		};
	}
	
	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Cost c) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setDouble(1, c.getAmount());
				ps.setString(2, c.getId());
			}
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(final Collection<Cost> persistable) {
		final List<Cost> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Cost c = l.get(i);
				ps.setString(1, c.getId());
				ps.setDouble(2, c.getAmount());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(final Collection<Cost> persistable) {
		final List<Cost> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Cost c = l.get(i);
				ps.setDouble(1, c.getAmount());
				ps.setString(2, c.getId());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}
}
