package spring.desai.common.repository.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.pojo.Student;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

import static spring.desai.common.utils.DataBaseConstants.*;

//@Profile(value = { "jdbc" })
@Repository(value="studentRepository")
public class StudentRepositoryImpl extends BaseJdbcRepository<Student> implements StudentRepository {

	@Override
	public Collection<Student> getStudentsForSubjectId(String subj_id) throws RepositoryDataAccessException {
		try {
			List<String> stud_ids = getJdbcTemplate().queryForList(getFieldFindBySql(SUBJECT_STUDENT_LINK_TABLE_NAME, SUBJ_ID, STUD_ID), new Object[] { subj_id }, String.class);
			if (stud_ids == null || stud_ids.isEmpty()) {
				return new ArrayList<>();
			}
			return getJdbcTemplate().query(getFindBySqlWhereIn(STUDENT_TABLE_NAME, ID, stud_ids.size()), stud_ids.toArray(), getRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
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
	
	private static final String insertSql = "INSERT INTO student (id, f_name, l_name, age, address) VALUES (?, ?, ?, ?, ?)";
	private static final String  updateSql = "UPDATE student set f_name = ?, l_name = ?, age = ?, address = ? where id = ?";
	
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
				ps.setString(2, s.getF_name());
				ps.setString(3, s.getL_name());
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
				ps.setString(1, s.getF_name());
				ps.setString(2, s.getL_name());
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
				ps.setString(2, s.getF_name());
				ps.setString(3, s.getL_name());
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
				ps.setString(1, s.getF_name());
				ps.setString(2, s.getL_name());
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
}
