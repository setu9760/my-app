package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.Scholarship;
import spring.desai.common.model.enums.ScholorshipType;

import static spring.desai.common.utils.DataBaseConstants.*;

@Component("scholarshipRowMapper")
public class ScholarshipRowMapper implements RowMapper<Scholarship> {

	@Override
	public Scholarship mapRow(ResultSet rs, int rowNum) throws SQLException {
		Scholarship scholorship = new Scholarship(rs.getString(ID), rs.getString(EXTERNAL_REF), ScholorshipType.valueOf(rs.getString(TYPE).toUpperCase()), 
				rs.getDouble(TOTAL_AMOUNT), rs.getDouble(PAID_AMOUNT), Boolean.valueOf(rs.getString(IS_FULLY_PAID)), 
				Boolean.valueOf(rs.getString(IS_FULLY_PAID)), rs.getString(ADDITIONAL_COMMENTS), rs.getString(STUD_ID));
		return scholorship;
	}
}
