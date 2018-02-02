package spring.desai.common.rowmappers;

import static spring.desai.common.utils.DataBaseConstants.AMOUNT;
import static spring.desai.common.utils.DataBaseConstants.COST_CODE;
import static spring.desai.common.utils.DataBaseConstants.IS_ACTIVE;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.Cost;

@Component("costRowMapper")
public class CostRowMapper implements RowMapper<Cost> {

	@Override
	public Cost mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cost c = new Cost(rs.getString(COST_CODE), rs.getDouble(AMOUNT));
		c.setIsActive(rs.getInt(IS_ACTIVE));
		return c;
	}

}
