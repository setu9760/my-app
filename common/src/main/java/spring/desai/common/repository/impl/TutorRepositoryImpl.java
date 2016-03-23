package spring.desai.common.repository.impl;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.RepositoryDataAccessException;
import spring.desai.common.repository.TutorRepository;
import spring.desai.common.utils.DataBaseConstants;

@Repository(value="tutorRepository")
public class TutorRepositoryImpl extends AbstractBaseRepository implements TutorRepository {

	@Override
	public Tutor findById(String id) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.TUTOR_TABLE_NAME, DataBaseConstants.ID), new Object[] { id }, getTutorRowMapper()).get(0);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Tutor> findbyName(String f_name) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.TUTOR_TABLE_NAME, DataBaseConstants.F_NAME), new Object[] { f_name }, getTutorRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void save(Persistable persistable) {
		Tutor tutor = (Tutor) persistable;
		try {
			getJdbcTemplate().update(getInsertSql(), new Object[] { tutor.getId(), tutor.getF_name(), tutor.getL_name(), tutor.getAddress(), tutor.isFulltime(), tutor.getSubject().getId()} );
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void update(Persistable persistable) {
		Tutor tutor = (Tutor) persistable;
		try {
			getJdbcTemplate().update(getUpdateSql(), new Object[] {tutor.getF_name(), tutor.getL_name(), tutor.getAddress(), tutor.isFulltime(), tutor.getSubject().getId(), tutor.getId()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Tutor> getAll() throws DataAccessException {
		return (Collection<Tutor>) getAllImpl(getSelectAllSql(DataBaseConstants.TUTOR_TABLE_NAME), Tutor.class);
	}
	
	@Override
	public Collection<Tutor> getTutorsForSubject(String subj_id) throws DataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.TUTOR_TABLE_NAME, DataBaseConstants.SUBJ_ID), new Object[] { subj_id }, getTutorRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.TUTOR_TABLE_NAME, DataBaseConstants.ID), id);
	}

	@Override
	public void deleteByName(String f_name) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.TUTOR_TABLE_NAME, DataBaseConstants.F_NAME), f_name);
	}

	@Override
	public int countAll() throws RepositoryDataAccessException {
		return countAllImpl(getCountAllSql(DataBaseConstants.TUTOR_TABLE_NAME));
	}
	
	@Override
	protected String getInsertSql() {
		return "INSERT INTO tutor VALUES (?, ?, ?, ?, ?, ?)";
	}
	
	@Override
	protected String getUpdateSql() {
		return "UPDATE tutor SET f_name = ?, l_name = ?, address = ?, isFulltime = ?, subj_id = ? WHERE id = ?";
	}
}
