package spring.desai.common.repository.impl.jdbc;

import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.STUDENT_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.SUBJECT_STUDENT_LINK_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.SUBJ_ID;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.Student;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository(value="studentRepository")
public class StudentRepositoryImpl extends BaseJdbcRepository<Student> implements StudentRepository {

	private static final String insertSql = "INSERT INTO student (id, f_name, l_name, age, address) VALUES (?, ?, ?, ?, ?)";
	private static final String  updateSql = "UPDATE student set f_name = ?, l_name = ?, age = ?, address = ? where id = ?";
	
	@Override
	public Collection<Student> getStudentsForSubjectId(String subj_id) throws RepositoryDataAccessException {
		List<String> stud_ids = getJdbcTemplate().queryForList(getFieldFindBySql(SUBJECT_STUDENT_LINK_TABLE_NAME, SUBJ_ID, STUD_ID), new Object[] { subj_id }, String.class);
		if (stud_ids == null || stud_ids.isEmpty()) {
			return new ArrayList<>();
		}
		return getJdbcTemplate().query(getFindBySqlWhereIn(STUDENT_TABLE_NAME, ID, stud_ids.size()), stud_ids.toArray(), getRowMapper());
	}
	
	@Override
	public void setActiveStatusById(String id, int status) throws RepositoryDataAccessException {
		getJdbcTemplate().update(getStatusSetSql(getTableName(), getIdField()), new Object[]{ status, id });
	}

	@Override
	protected RowMapper<Student> getRowMapper() {
		return studentRowMapper;
	}

	@Override
	protected String getInsertSql() {
		return insertSql;
	}
	
	@Override
	protected String getUpdateSql() {
		return updateSql;
	}
	
	@Override
	protected String getTableName() {
		return STUDENT_TABLE_NAME;
	}

	@Override
	protected String getNameField() {
		return F_NAME;
	}

	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final Student s) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, s.getId());
				ps.setString(2, s.getFirstname());
				ps.setString(3, s.getLastname());
				ps.setInt(4, s.getAge());
				ps.setString(5, s.getAddress());
			}
		};
	}
	
	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Student s) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, s.getFirstname());
				ps.setString(2, s.getLastname());
				ps.setInt(3, s.getAge());
				ps.setString(4, s.getAddress());
				ps.setString(5, s.getId());
			}
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(final Collection<Student> persistable) {
		final ArrayList<Student> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Student s = l.get(i);
				ps.setString(1, s.getId());
				ps.setString(2, s.getFirstname());
				ps.setString(3, s.getLastname());
				ps.setInt(4, s.getAge());
				ps.setString(5, s.getAddress());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(final Collection<Student> persistable) {
		final ArrayList<Student> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Student s = l.get(i);
				ps.setString(1, s.getFirstname());
				ps.setString(2, s.getLastname());
				ps.setInt(3, s.getAge());
				ps.setString(4, s.getAddress());
				ps.setString(5, s.getId());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}
	
	@Override
	protected boolean isDeleteByNameOpSupported() {
		return true;
	}
}
