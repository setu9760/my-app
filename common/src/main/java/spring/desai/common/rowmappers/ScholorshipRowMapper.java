package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.model.pojo.Scholorship;

public class ScholorshipRowMapper implements RowMapper<Scholorship> {

	@Override
	public Scholorship mapRow(ResultSet rs, int rowNum) throws SQLException {
		Scholorship scholorship = new Scholorship(rs.getString(1), rs.getString(2), ScholorshipType.valueOf(rs.getString(3)), rs.getDouble(4), rs.getDouble(5), Boolean.valueOf(rs.getString(6)), Boolean.valueOf(rs.getString(7)), rs.getString(8), rs.getString(9));
		return scholorship;
	}
}
