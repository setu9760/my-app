package spring.desai.common.rowmappers;

import static spring.desai.common.utils.DataBaseConstants.COST_CODE;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.IS_MANDATORY;
import static spring.desai.common.utils.DataBaseConstants.NAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.pojo.Subject;

@Component("subjectRowMapper")
public class SubjectRowMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Subject subject = null;
		subject = new Subject(resultSet.getString(ID), resultSet.getString(NAME), 
				resultSet.getString(COST_CODE), resultSet.getBoolean(IS_MANDATORY));
		return subject;
	}
}
