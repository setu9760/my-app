package spring.desai.common.repository.impl.jpa;

import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.STUDENT_TABLE_NAME;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import spring.desai.common.model.Student;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

//@Profile("jpa")
@Repository("studentRepository")
public class StudentRepositoryImplJpa extends BaseJpaRepository<Student> implements StudentRepository {

	
	@Override
	protected Class<Student> getEntityClass() {
		return Student.class;
	}
	
	@Override
	public Collection<Student> getStudentsForSubjectId(String subj_id) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getInsertSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getUpdateSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNameField() {
		return F_NAME;
	}
	
	@Override
	protected String getTableName() {
		return STUDENT_TABLE_NAME;
	}

	@Override
	public void saveAll(Collection<Student> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAll(Collection<Student> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		
	}
}
