package spring.desai.common.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.ScholorshipRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.DataBaseConstants;

@Repository("scholorshipRepository")
public class ScholorshipRepositoryImpl extends AbstractBaseRepository implements ScholorshipRepository{

	@Override
	public void save(Scholorship scholorship) throws RepositoryDataAccessException  {
		try {
			getJdbcTemplate().update(getInsertSql(), new Object[] {scholorship.getId(), scholorship.getExternal_ref(), scholorship.getType().toString(), scholorship.getTotal_amount(), 
					scholorship.getPaid_amount(), scholorship.isFullyPaid(), scholorship.isPostPay(), scholorship.getStud_id(), scholorship.getAdditional_comments()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}		
	}
	
	public void saveAll(final Collection<Scholorship> persistables) throws RepositoryDataAccessException {
		try {
			final List<Scholorship> scholorships = new ArrayList<>(persistables);
			
			getJdbcTemplate().batchUpdate(getInsertSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Scholorship sch = scholorships.get(i);
					ps.setString(1, sch.getId());
					ps.setString(2, sch.getExternal_ref());
					ps.setString(3, sch.getType().toString());
					ps.setDouble(4, sch.getTotal_amount());
					ps.setDouble(5, sch.getPaid_amount());
					ps.setString(6, String.valueOf(sch.isFullyPaid()));
					ps.setString(7, String.valueOf(sch.isPostPay()));
					ps.setString(8, sch.getStud_id());
					ps.setString(9, sch.getAdditional_comments());
				}
				
				@Override
				public int getBatchSize() {
					return persistables.size();
				}
			});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void update(Scholorship scholorship) throws RepositoryDataAccessException {
		try {
			getJdbcTemplate().update(getUpdateSql(), new Object[] {scholorship.getExternal_ref(), scholorship.getType().toString(), scholorship.getTotal_amount(), 
					scholorship.getPaid_amount(), scholorship.isFullyPaid(), scholorship.isPostPay(), scholorship.getStud_id(), scholorship.getAdditional_comments(), scholorship.getId()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void updateAll(final Collection<Scholorship> persistables) throws RepositoryDataAccessException{
		try {
			final List<Scholorship> scholorships = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getUpdateSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Scholorship sch = scholorships.get(i);
					ps.setString(1, sch.getExternal_ref());
					ps.setString(2, sch.getType().toString());
					ps.setDouble(3, sch.getTotal_amount());
					ps.setDouble(4, sch.getPaid_amount());
					ps.setString(5, String.valueOf(sch.isFullyPaid()));
					ps.setString(6, String.valueOf(sch.isPostPay()));
					ps.setString(7, sch.getStud_id());
					ps.setString(8, sch.getAdditional_comments());
					ps.setString(9, sch.getId());
				}
				
				@Override
				public int getBatchSize() {
					return persistables.size();
				}
			});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Scholorship findById(String id) throws RepositoryDataAccessException {
		try {
			List<Scholorship> l = getJdbcTemplate().query(getFindBySql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME, DataBaseConstants.ID), new Object[] { id }, getScholorshipRowMapper());
			return (l != null && !l.isEmpty()) ? l.get(0) : null;
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	/**
	 * No-op
	 */
	@Override
	public Collection<Scholorship> findByName(String name) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Scholorship> findByType(ScholorshipType type) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME, DataBaseConstants.TYPE), new Object[] { type.toString() }, getScholorshipRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public Collection<Scholorship> findByStudentId(String stud_id) throws RepositoryDataAccessException{
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME, DataBaseConstants.STUD_ID), new Object[] { stud_id }, getScholorshipRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Scholorship> getAll() throws RepositoryDataAccessException {
		return (Collection<Scholorship>) getAllImpl(getSelectAllSql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME), Scholorship.class);
	}

	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME, DataBaseConstants.ID), id);
	}
	
	/**
	 * No-op
	 */
	@Override
	public void deleteAll() throws RepositoryDataAccessException{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int countAll() throws RepositoryDataAccessException {
		return countAllImpl(getCountAllSql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME));
	}

	@Override
	protected String getInsertSql() {
		return "INSERT INTO scholorship (id, external_ref, type, total_amount, paid_amount, isFullyPaid, isPostPay, stud_id, additional_comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE scholorship SET external_ref = ?, type = ?, total_amount = ?, paid_amount = ?, isFullyPaid = ?, isPostPay = ?, stud_id = ?, additional_comments = ? WHERE id = ?";
	}

}
