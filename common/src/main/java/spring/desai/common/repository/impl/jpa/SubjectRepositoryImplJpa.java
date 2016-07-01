package spring.desai.common.repository.impl.jpa;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import spring.desai.common.model.Subject;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("subjectRepository")
public class SubjectRepositoryImplJpa extends BaseJpaRepository<Subject> implements SubjectRepository {

	@Override
	public void saveAll(Collection<Subject> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAll(Collection<Subject> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	protected Class<Subject> getEntityClass() {
		return Subject.class;
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
	protected String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNameField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Subject> getSubjectsForStudentId(String studentId) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Subject> findByCostCode(String costCode) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Subject> findByMandatory(boolean isMandatory) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStudentToSubject(String studentId, Subject subject) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeStudentFromSubject(String studentId, Subject subject) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStudentInSubject(String studentId, Subject subject) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

}
