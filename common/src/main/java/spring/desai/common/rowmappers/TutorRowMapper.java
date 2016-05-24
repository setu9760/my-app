package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.utils.DataBaseConstants;

public class TutorRowMapper implements RowMapper<Tutor> {

	@Override
	public Tutor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Tutor tutor = null;
		tutor = new Tutor(resultSet.getString(DataBaseConstants.ID), resultSet.getString(DataBaseConstants.F_NAME), resultSet.getString(DataBaseConstants.L_NAME), 
				resultSet.getString(DataBaseConstants.SUBJ_ID), resultSet.getString(DataBaseConstants.ADDRESS) ,resultSet.getBoolean(DataBaseConstants.IS_FULLTIME));
		return tutor;
	}

}
