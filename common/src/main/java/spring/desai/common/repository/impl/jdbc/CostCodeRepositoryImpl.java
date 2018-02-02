package spring.desai.common.repository.impl.jdbc;

import static spring.desai.common.utils.DataBaseConstants.COST_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.COST_CODE;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.Cost;
import spring.desai.common.repository.CostCodeRepository;

@Repository("costCodeRepository")
public class CostCodeRepositoryImpl extends BaseJdbcRepository<Cost> implements CostCodeRepository {
	
	@Override
	protected RowMapper<Cost> getRowMapper() {
		return costRowMapper;
	}
	
	@Override
	protected String getInsertSql() {
		return "INSERT INTO cost VALUES (?, ?, 1)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE COST SET amount = ? WHERE cost_code = ?";
	}

	@Override
	protected String getTableName() {
		return COST_TABLE_NAME;
	}
	
	@Override
	protected String getIdField() {
		return COST_CODE;
	}
	
	@Override
	protected String getNameField() {
		return COST_CODE;
	}
	
	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final Cost c) {
		return ps -> {
				ps.setString(1, c.getId());
				ps.setDouble(2, c.getAmount());
		};
	}
	
	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Cost c) {
		return ps -> {
				ps.setDouble(1, c.getAmount());
				ps.setString(2, c.getId());
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
