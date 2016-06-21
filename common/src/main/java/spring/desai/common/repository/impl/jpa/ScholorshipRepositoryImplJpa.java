package spring.desai.common.repository.impl.jpa;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.repository.ScholorshipRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("scholorshipRepository")
public class ScholorshipRepositoryImplJpa extends BaseJpaRepository<Scholorship> implements ScholorshipRepository {

	@Override
	public void saveAll(Collection<Scholorship> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAll(Collection<Scholorship> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Scholorship> findByType(ScholorshipType type) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Scholorship> findByStudentId(String stud_id) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Scholorship> getEntityClass() {
		return Scholorship.class;
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
