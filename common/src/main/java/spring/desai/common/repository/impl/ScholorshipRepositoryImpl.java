package spring.desai.common.repository.impl;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.RepositoryDataAccessException;
import spring.desai.common.repository.ScholorshipRepository;
import spring.desai.common.utils.DataBaseConstants;

@Repository("scholorshipRepository")
public class ScholorshipRepositoryImpl extends AbstractBaseRepository implements ScholorshipRepository{

	@Override
	public void save(Persistable persistable) throws RepositoryDataAccessException  {
		try {
			Scholorship scholorship = (Scholorship) persistable;
			getJdbcTemplate().update(getInsertSql(), new Object[] {scholorship.getId(), scholorship.getExternal_ref(), scholorship.getType().toString(), scholorship.getTotal_amount(), scholorship.getPaid_amount(), scholorship.isFullyPaid(), scholorship.isPostPay(), scholorship.getStud_id(), scholorship.getAdditional_comments()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}		
	}

	@Override
	public void update(Persistable persistable) throws RepositoryDataAccessException {
		try {
			Scholorship scholorship = (Scholorship) persistable;
			getJdbcTemplate().update(getUpdateSql(), new Object[] {scholorship.getExternal_ref(), scholorship.getType().toString(), scholorship.getTotal_amount(), scholorship.getPaid_amount(), scholorship.isFullyPaid(), scholorship.isPostPay(), scholorship.getStud_id(), scholorship.getAdditional_comments(), scholorship.getId()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public int countAll() throws RepositoryDataAccessException {
		return countAllImpl(getCountAllSql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME));
	}

	@Override
	public Scholorship findById(String id) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME, DataBaseConstants.ID), new Object[] { id }, getScholorshipRowMapper()).get(0);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Scholorship> findByType(ScholorshipType type) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME, DataBaseConstants.TYPE), new Object[] { type.toString() }, getScholorshipRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public Collection<Scholorship> findByStudent(String stud_id) throws RepositoryDataAccessException{
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME, DataBaseConstants.STUD_ID), new Object[] { stud_id }, getScholorshipRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.SCHOLORSHIP_TABLE_NAME, DataBaseConstants.ID), id);
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
