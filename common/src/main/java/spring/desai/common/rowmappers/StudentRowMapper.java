package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.pojo.Student;
import spring.desai.common.utils.DataBaseConstants;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Student student = null;
			student = new Student();
			student.setId(resultSet.getString(DataBaseConstants.ID));
			student.setF_name(resultSet.getString(DataBaseConstants.F_NAME));
			student.setL_name(resultSet.getString(DataBaseConstants.L_NAME));
			student.setAge(resultSet.getInt(DataBaseConstants.AGE));
			student.setAddress(resultSet.getString(DataBaseConstants.ADDRESS));
		return student;
	}
}
