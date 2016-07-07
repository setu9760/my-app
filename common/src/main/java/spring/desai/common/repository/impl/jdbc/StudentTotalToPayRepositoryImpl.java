package spring.desai.common.repository.impl.jdbc;

import static org.springframework.util.Assert.notNull;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.STUD_TOTAL_PAY_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.TOTAL_TO_PAY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.desai.common.repository.StudentTotalToPayRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.I18N;

@Repository("studentTotalToPayRepository")
public class StudentTotalToPayRepositoryImpl implements StudentTotalToPayRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public double updateTotalToPayBy(String studId, double addToTotal) throws RepositoryDataAccessException{
		notNull(studId, I18N.getString("error.null.id"));
		try {
			
			Double existingTotal = getCurrentTotalToPay(studId);
			
			if (existingTotal != 0d) {
				existingTotal += addToTotal;
				getJdbcTemplate().update("update " + STUD_TOTAL_PAY_TABLE_NAME + " set " + TOTAL_TO_PAY + " = ? where " + STUD_ID + " = ?", new Object[] {existingTotal, studId});
			} else {
				existingTotal = addToTotal;
				getJdbcTemplate().update("insert into " + STUD_TOTAL_PAY_TABLE_NAME + " values (?, ?)", new Object[] {studId, existingTotal});
			}
			return existingTotal;
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public double getCurrentTotalToPay(String studId) throws RepositoryDataAccessException{
		try {
			Double existingTotal = getJdbcTemplate().queryForObject("select sum(" + TOTAL_TO_PAY + ") from " + STUD_TOTAL_PAY_TABLE_NAME + " where " + STUD_ID + " = ?",
					new Object[] { studId }, Double.class);
			if (existingTotal == null) {
				existingTotal = 0d;
			}
			return existingTotal;
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
}
