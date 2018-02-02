package spring.desai.common.rowmappers;

import static spring.desai.common.utils.DataBaseConstants.ADDRESS;
import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.IS_ACTIVE;
import static spring.desai.common.utils.DataBaseConstants.IS_FULLTIME;
import static spring.desai.common.utils.DataBaseConstants.L_NAME;
import static spring.desai.common.utils.DataBaseConstants.SUBJ_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.Tutor;

@Component("tutorRowMapper")
public class TutorRowMapper implements RowMapper<Tutor> {

	@Override
	public Tutor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tutor t = new Tutor(rs.getString(ID), rs.getString(F_NAME), rs.getString(L_NAME), rs.getString(SUBJ_ID),
				rs.getString(ADDRESS), rs.getBoolean(IS_FULLTIME));
		t.setIsActive(rs.getInt(IS_ACTIVE));
		return t;
	}

}
