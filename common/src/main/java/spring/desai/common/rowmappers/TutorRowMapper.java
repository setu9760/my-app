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
		tutor = new Tutor();
		tutor.setId(resultSet.getString(DataBaseConstants.ID));
		tutor.setF_name(DataBaseConstants.F_NAME);
		tutor.setL_name(DataBaseConstants.L_NAME);
		tutor.setSubj_id(DataBaseConstants.SUBJ_ID);
		return tutor;
	}

}
