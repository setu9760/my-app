package spring.desai.common.repository;

import spring.desai.common.model.pojo.Persistable;

public interface BasePersistableRepository {

	void save(Persistable persistable);

	void update(Persistable persistable);
	
	void deleteById(String id);
	
	int countAll();
}
