package spring.desai.common.repository.impl;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.model.pojo.Student;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.RepositoryDataAccessException;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.utils.DataBaseConstants;

@Repository(value="studentRepository")
public class StudentRepositoryImpl extends AbstractBaseRepository implements StudentRepository {
	
	@Override
	public Student findById(String id) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.STUDENT_TABLE_NAME, DataBaseConstants.ID), new Object[] { id }, getStudentRowMapper()).get(0);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Student> findbyName(String name) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.STUDENT_TABLE_NAME, DataBaseConstants.F_NAME), new Object [] { name },  getStudentRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void save(Persistable persistable) throws RepositoryDataAccessException {
		Student student = (Student) persistable;
		try {
			getJdbcTemplate().update(getInsertSql(), new Object[] { student.getId(), student.getF_name(), student.getL_name(), student.getAge(), student.getAddress() });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Student> getAll() throws RepositoryDataAccessException {
		return (Collection<Student>) getAllImpl(getSelectAllSql(DataBaseConstants.STUDENT_TABLE_NAME), Student.class);
	}

	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.STUDENT_TABLE_NAME, DataBaseConstants.ID), id);
	}

	@Override
	public void deleteByName(String f_name) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.STUDENT_TABLE_NAME, DataBaseConstants.F_NAME), f_name);
	}

	@Override
	public int countAll() throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().queryForObject(getCountAllSql(DataBaseConstants.STUDENT_TABLE_NAME), Integer.class);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void update(Persistable persistable) throws RepositoryDataAccessException {
		Student student = (Student) persistable;
		try {
			getJdbcTemplate().update(getUpdateSql(), new Object[] { student.getF_name(), student.getL_name(), student.getAge(), student.getAddress(), student.getId() });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	protected String getInsertSql() {
		return insertSql;
	}
	
	@Override
	protected String getUpdateSql() {
		return updateSql;
	}
	
	private static final String insertSql = "INSERT INTO student NAMES (id, f_name, l_name, age, address) VALUES (?, ?, ?, ?, ?)";
	private static final String  updateSql = "UPDATE student set f_name = ?, l_name = ?, age = ?, address = ? where id = ?";
}
