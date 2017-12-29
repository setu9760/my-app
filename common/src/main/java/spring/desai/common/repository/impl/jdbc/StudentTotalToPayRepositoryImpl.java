package spring.desai.common.repository.impl.jdbc;

import static org.springframework.util.Assert.notNull;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.STUD_TOTAL_PAY_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.TOTAL_TO_PAY;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Double updateTotalToPay(String studId, double newTotalToPay) throws RepositoryDataAccessException{
		notNull(studId, I18N.getString("error.null.id"));
		getJdbcTemplate().update("update " + STUD_TOTAL_PAY_TABLE_NAME + " set " + TOTAL_TO_PAY + " = ? where " + STUD_ID + " = ?", new Object[] {newTotalToPay, studId});
		return getCurrentTotalToPay(studId);
	}
	
	@Override
	public Double getCurrentTotalToPay(String studId) throws RepositoryDataAccessException{
		Double existingTotal = getJdbcTemplate().queryForObject("select sum(" + TOTAL_TO_PAY + ") from " + STUD_TOTAL_PAY_TABLE_NAME + " where " + STUD_ID + " = ?",
				new Object[] { studId }, Double.class);
		if (existingTotal == null)
			existingTotal = 0d;
		return existingTotal;
	}
	
	@Override
	public void addDefaultTotalToPayRow(String studId) throws RepositoryDataAccessException {
		notNull(studId, I18N.getString("error.null.id"));
		getJdbcTemplate().update("insert into " + STUD_TOTAL_PAY_TABLE_NAME + " values (?, ?)", new Object[] {studId, 0});
	}

	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
}
