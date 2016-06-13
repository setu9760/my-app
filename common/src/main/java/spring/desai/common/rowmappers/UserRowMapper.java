package spring.desai.common.rowmappers;

import static spring.desai.common.utils.DataBaseConstants.ACCOUNT_LOCKED;
import static spring.desai.common.utils.DataBaseConstants.ADDRESS;
import static spring.desai.common.utils.DataBaseConstants.FAILED_ATTEMPTS;
import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.L_NAME;
import static spring.desai.common.utils.DataBaseConstants.SIGN_ON_STATUS;
import static spring.desai.common.utils.DataBaseConstants.USER_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.pojo.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new User(rs.getString(USER_ID), rs.getString(F_NAME), rs.getString(L_NAME), rs.getString(ADDRESS), 
				rs.getInt(FAILED_ATTEMPTS),	Boolean.valueOf(rs.getString(ACCOUNT_LOCKED)), Boolean.valueOf(rs.getString(SIGN_ON_STATUS)));
	}

}
