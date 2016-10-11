package spring.desai.common.repository.impl.jpa;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import spring.desai.common.model.Scholarship;
import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.repository.ScholarshipRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("scholarshipRepository")
public class ScholarshipRepositoryImplJpa extends BaseJpaRepository<Scholarship> implements ScholarshipRepository {

	@Override
	public void saveAll(Collection<Scholarship> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAll(Collection<Scholarship> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Scholarship> findByType(ScholorshipType type) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Scholarship> findByStudentId(String stud_id) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Scholarship> getEntityClass() {
		return Scholarship.class;
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
