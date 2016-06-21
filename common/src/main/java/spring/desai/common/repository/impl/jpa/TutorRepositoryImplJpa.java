package spring.desai.common.repository.impl.jpa;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.repository.TutorRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("tutorRepository")
public class TutorRepositoryImplJpa extends BaseJpaRepository<Tutor> implements TutorRepository {

	@Override
	public void saveAll(Collection<Tutor> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAll(Collection<Tutor> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Tutor> getTutorsForSubject(String subj_id) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Tutor> getEntityClass() {
		return Tutor.class;
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

}
