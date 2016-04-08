package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Cost;
import spring.desai.common.utils.DataBaseConstants;

public class CostRowMapper implements RowMapper<Cost> {

	@Override
	public Cost mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Cost(CostCode.valueOf(rs.getString(DataBaseConstants.COST_CODE)), rs.getDouble(DataBaseConstants.AMOUNT));
	}

}
