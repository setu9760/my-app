package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Subject;
import static spring.desai.common.utils.DataBaseConstants.*;

public class SubjectRowMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Subject subject = null;
		subject = new Subject(resultSet.getString(ID), resultSet.getString(NAME), 
				CostCode.valueOf(resultSet.getString(COST_CODE)), resultSet.getBoolean(IS_MANDATORY));
		return subject;
	}
}
