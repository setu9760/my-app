package spring.desai.common.repository.impl;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.model.pojo.Scholorship.ScholorshipType;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.ScholorshipRepository;

@Repository("scholorshipRepository")
public class ScholorshipRepositoryImpl extends AbstractBaseRepository implements ScholorshipRepository{

	@Override
	public void save(Persistable persistable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Persistable persistable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Scholorship findById(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Scholorship> findByType(ScholorshipType type) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		
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

}
