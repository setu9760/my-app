package spring.desai.common.repository.tests;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.utils.I18N;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractRepositoryTest {

	protected void doNullSaveTest(BasePersistableRepository<? extends Persistable> repo){
		try {
			repo.save(null);
			fail("Should have thrown IllegalArgumentException while saving");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
	}
	
	protected void doNullUpdateTest(BasePersistableRepository<? extends Persistable> repo){
		try {
			repo.update(null);
			fail("Should have thrown IllegalArgumentException while updating");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
	}
	
	protected void testPersistableFindById(BasePersistableRepository<?> repo, String id){
		Persistable p = null;
		p = repo.findById(null);
		assertThat(p, is(nullValue()));
		
		p = repo.findById("");
		assertThat(p, is(nullValue()));
		
		p = repo.findById("NON_EXISTANT_ID");
		assertThat(p, is(nullValue()));
		
		p = repo.findById(id);
		assertThat(p, is(not(nullValue())));
		assertThat(p.getId(), is(equalTo(id)));
	}
	
	protected void testPersistableFindByName(BasePersistableRepository<?> repo, String name, int nameRecordsCount, int totalCount){
		Collection<?> c = null;
		c = repo.findByName(null);
		assertThat(c, is(not(nullValue())));
		assertThat(c, is(empty()));
		
		c = repo.findByName("NON_EXISTANT_NAME");
		assertThat(c, is(not(nullValue())));
		assertThat(c, is(empty()));
		
		c = repo.findByName(name);
		assertThat(c, hasSize(nameRecordsCount));
		
		c = repo.findByName(name.toLowerCase());
		assertThat(c, hasSize(nameRecordsCount));
		
		c = repo.findByName(name.toUpperCase());
		assertThat(c, hasSize(nameRecordsCount));
		
		c = repo.findByName(name.substring(0, name.length()/2));
		assertThat(c, hasSize(totalCount));
		
		c = repo.findByName("");
		assertThat(c, is(not(nullValue())));
		assertThat(c, hasSize(totalCount));
	}
	

	protected void testPersistableGetAll(BasePersistableRepository<?> repo, int totalCount) {
		Collection<?> c = repo.getAll();
		assertThat(c, is(not(nullValue())));
		assertThat(c, hasSize(totalCount));
	}
	
	protected void testPersistableDeleteAll(BasePersistableRepository<?> repo) {
		repo.deleteAll();
	}
	
	protected void testPersistableCountAll(BasePersistableRepository<?> repo, int expectedCount) {
		int actualCount = repo.countAll();
		assertThat(actualCount, is(equalTo(expectedCount)));
	}
	
}
