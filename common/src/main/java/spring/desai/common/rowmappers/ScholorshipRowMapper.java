package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.model.pojo.Scholorship;
import static spring.desai.common.utils.DataBaseConstants.*;

@Component("scholorshipRowMapper")
public class ScholorshipRowMapper implements RowMapper<Scholorship> {

	@Override
	public Scholorship mapRow(ResultSet rs, int rowNum) throws SQLException {
		Scholorship scholorship = new Scholorship(rs.getString(ID), rs.getString(EXTERNAL_REF), ScholorshipType.valueOf(rs.getString(TYPE).toUpperCase()), 
				rs.getDouble(TOTAL_AMOUNT), rs.getDouble(PAID_AMOUNT), Boolean.valueOf(rs.getString(IS_FULLY_PAID)), 
				Boolean.valueOf(rs.getString(IS_FULLY_PAID)), rs.getString(ADDITIONAL_COMMENTS), rs.getString(STUD_ID));
		return scholorship;
	}
}
