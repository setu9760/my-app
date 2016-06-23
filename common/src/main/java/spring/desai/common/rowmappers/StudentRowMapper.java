package spring.desai.common.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import spring.desai.common.model.Student;

import static spring.desai.common.utils.DataBaseConstants.*;

@Component("studentRowMapper")
public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Student student = null;
		student = new Student(resultSet.getString(ID), resultSet.getString(F_NAME), resultSet.getString(L_NAME), 
				resultSet.getInt(AGE), resultSet.getString(ADDRESS));
		return student;
	}
}
