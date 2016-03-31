package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.utils.DataBaseConstants;

public class ScholorshipRowMapper implements RowMapper<Scholorship> {

	@Override
	public Scholorship mapRow(ResultSet rs, int rowNum) throws SQLException {
		Scholorship scholorship = new Scholorship(rs.getString(DataBaseConstants.ID), rs.getString(DataBaseConstants.EXTERNAL_REF), ScholorshipType.valueOf(rs.getString(DataBaseConstants.TYPE)), 
				rs.getDouble(DataBaseConstants.TOTAL_AMOUNT), rs.getDouble(DataBaseConstants.PAID_AMOUNT), Boolean.valueOf(rs.getString(DataBaseConstants.IS_FULLY_PAID)), 
				Boolean.valueOf(rs.getString(DataBaseConstants.IS_FULLY_PAID)), rs.getString(DataBaseConstants.ADDITIONAL_COMMENTS), rs.getString(DataBaseConstants.STUD_ID));
		return scholorship;
	}
}
