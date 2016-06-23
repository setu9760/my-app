package spring.desai.common.rowmappers;

import static spring.desai.common.utils.DataBaseConstants.ACCOUNT_NON_LOCKED;
import static spring.desai.common.utils.DataBaseConstants.ADDRESS;
import static spring.desai.common.utils.DataBaseConstants.FAILED_ATTEMPTS;
import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.L_NAME;
import static spring.desai.common.utils.DataBaseConstants.USER_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.User;
import spring.desai.common.model.User.SIGN_ON_STATUS;
import spring.desai.common.utils.DataBaseConstants;

@Component("userRowMapper")
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new User(rs.getString(USER_ID), rs.getString(F_NAME), rs.getString(L_NAME), rs.getString(ADDRESS), rs.getInt(FAILED_ATTEMPTS),	
				Boolean.valueOf(rs.getString(ACCOUNT_NON_LOCKED)), SIGN_ON_STATUS.valueOf(rs.getString(DataBaseConstants.SIGN_ON_STATUS)));
	}

}
