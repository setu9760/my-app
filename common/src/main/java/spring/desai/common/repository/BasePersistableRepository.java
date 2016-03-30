package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.pojo.Persistable;

public interface BasePersistableRepository {

	void save(Persistable persistable);

	void saveAll(Collection<? extends Persistable> persistables);
	
	void update(Persistable persistable);
	
	void updateAll(Collection<? extends Persistable> persistables);
	
	void deleteById(String id);
	
	void deleteAll();
	
	int countAll();
}
