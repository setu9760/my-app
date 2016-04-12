package spring.desai.common.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.pojo.Student;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.DataBaseConstants;

@Repository(value="studentRepository")
public class StudentRepositoryImpl extends AbstractBaseRepository implements StudentRepository {

	@Override
	public void save(Student student) {
		try {
			checkNotNull(student);
			getJdbcTemplate().update(getInsertSql(), new Object[] { student.getId(), student.getF_name(), student.getL_name(), student.getAge(), student.getAddress() });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void saveAll(final Collection<Student> persistables) throws RepositoryDataAccessException{
		try {
			final List<Student> students = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getInsertSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Student s = students.get(i);
					ps.setString(1, s.getId());
					ps.setString(2, s.getF_name());
					ps.setString(3, s.getL_name());
					ps.setInt(4, s.getAge());
					ps.setString(5, s.getAddress());
				}
				
				@Override
				public int getBatchSize() {
					return persistables.size();
				}
			});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}		
	}
	
	@Override
	public void update(Student student) {
		try {
			getJdbcTemplate().update(getUpdateSql(), new Object[] { student.getF_name(), student.getL_name(), student.getAge(), student.getAddress(), student.getId() });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	
	@Override
	public void updateAll(final Collection<Student> persistables) throws RepositoryDataAccessException {
		try {
			final List<Student> studs = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getUpdateSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Student s = studs.get(i);
					ps.setString(1, s.getF_name());
					ps.setString(2, s.getL_name());
					ps.setInt(3, s.getAge());
					ps.setString(4, s.getAddress());
					ps.setString(5, s.getId());
				}
				
				@Override
				public int getBatchSize() {
					return persistables.size();
				}
			});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Student findById(String id) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.STUDENT_TABLE_NAME, DataBaseConstants.ID), new Object[] { id }, getStudentRowMapper()).get(0);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Student> findByName(String name) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.STUDENT_TABLE_NAME, DataBaseConstants.F_NAME), new Object [] { name },  getStudentRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Student> getAll() throws RepositoryDataAccessException {
		return (Collection<Student>) getAllImpl(getSelectAllSql(DataBaseConstants.STUDENT_TABLE_NAME), Student.class);
	}
	
	@Override
	public Collection<Student> getStudentsForSubjectId(String subj_id) throws RepositoryDataAccessException {
		try {
			List<String> stud_ids = getJdbcTemplate().queryForList(getFieldFindBySql(DataBaseConstants.SUBJECT_STUDENT_LINK_TABLE_NAME, DataBaseConstants.SUBJ_ID, DataBaseConstants.STUD_ID), new Object[] { subj_id }, String.class);
			if (stud_ids == null || stud_ids.isEmpty()) {
				return null;
			}
			return getJdbcTemplate().query(getFindBySqlWhereIn(DataBaseConstants.STUDENT_TABLE_NAME, DataBaseConstants.ID, stud_ids.size()), stud_ids.toArray(), getStudentRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.STUDENT_TABLE_NAME, DataBaseConstants.ID), id);
	}

	@Override
	public void deleteByName(String f_name) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.STUDENT_TABLE_NAME, DataBaseConstants.F_NAME), f_name);
	}
	
	/**
	 * No-op
	 */
	@Override
	public void deleteAll() throws RepositoryDataAccessException {
		try {
			// TODO
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
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
	protected String getInsertSql() {
		return insertSql;
	}
	
	@Override
	protected String getUpdateSql() {
		return updateSql;
	}
	
	private static final String insertSql = "INSERT INTO student (id, f_name, l_name, age, address) VALUES (?, ?, ?, ?, ?)";
	private static final String  updateSql = "UPDATE student set f_name = ?, l_name = ?, age = ?, address = ? where id = ?";

}
