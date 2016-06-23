package spring.desai.common.rowmappers;

import static spring.desai.common.utils.DataBaseConstants.DESCRIPTION;
import static spring.desai.common.utils.DataBaseConstants.ROLE;
import static spring.desai.common.utils.DataBaseConstants.ROLE_FULL;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.Role;

@Component("rolesRowMapper")
public class RolesRowMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Role(rs.getString(ROLE), rs.getString(ROLE_FULL), rs.getString(DESCRIPTION));
	}

}
