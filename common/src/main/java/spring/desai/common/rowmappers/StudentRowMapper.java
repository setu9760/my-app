package spring.desai.common.rowmappers;

import static spring.desai.common.utils.DataBaseConstants.ADDRESS;
import static spring.desai.common.utils.DataBaseConstants.AGE;
import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.IS_ACTIVE;
import static spring.desai.common.utils.DataBaseConstants.L_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.Student;

@Component("studentRowMapper")
public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student s = new Student(rs.getString(ID), rs.getString(F_NAME), rs.getString(L_NAME), 
				rs.getInt(AGE), rs.getString(ADDRESS));
		s.setIsActive(rs.getInt(IS_ACTIVE));
		return s;
	}
}
