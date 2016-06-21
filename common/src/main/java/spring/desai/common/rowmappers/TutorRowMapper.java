package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.pojo.Tutor;
import static spring.desai.common.utils.DataBaseConstants.*;

@Component("tutorRowMapper")
public class TutorRowMapper implements RowMapper<Tutor> {

	@Override
	public Tutor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Tutor tutor = null;
		tutor = new Tutor(resultSet.getString(ID), resultSet.getString(F_NAME), resultSet.getString(L_NAME), 
				resultSet.getString(SUBJ_ID), resultSet.getString(ADDRESS) ,resultSet.getBoolean(IS_FULLTIME));
		return tutor;
	}

}
