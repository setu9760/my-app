package spring.desai.common.rowmappers;

import static spring.desai.common.utils.DataBaseConstants.COST_CODE;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.IS_ACTIVE;
import static spring.desai.common.utils.DataBaseConstants.IS_MANDATORY;
import static spring.desai.common.utils.DataBaseConstants.NAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.Subject;

@Component("subjectRowMapper")
public class SubjectRowMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
		Subject s = new Subject(rs.getString(ID), rs.getString(NAME), rs.getString(COST_CODE), rs.getBoolean(IS_MANDATORY));
		s.setIsActive(rs.getInt(IS_ACTIVE));
		return s;
	}
}
