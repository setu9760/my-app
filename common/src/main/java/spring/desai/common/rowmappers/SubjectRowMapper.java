package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.pojo.Subject;
import spring.desai.common.utils.DataBaseConstants;

public class SubjectRowMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Subject subject = null;
		subject = new Subject();
		subject.setId(resultSet.getString(DataBaseConstants.ID));
		subject.setName(resultSet.getString(DataBaseConstants.NAME));
		subject.setMandatory(resultSet.getBoolean(DataBaseConstants.IS_MANDATORY));
		return subject;
	}
}
