package spring.desai.common.repository.tests;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.I18N;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
@ActiveProfiles(profiles = { "jdbc" })
public class AbstractRepositoryTest<T extends Persistable> {

	protected void doNullSaveTest(BasePersistableRepository<T> repo){
		try {
			repo.save(null);
			fail("Should have thrown IllegalArgumentException while saving");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
	}
	
	protected void doNullIdSaveTest(BasePersistableRepository<T> repo, T persistable){
		try {
			repo.save(persistable);
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}
	}
	
	protected void doNullFieldSaveTest(BasePersistableRepository<T> repo, T persistable){
		try {
			repo.save(persistable);
			fail("Should have thrown DataAccessException while saving null to non-null column");
		} catch (Exception e) {
			assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
		}
	}
	
	protected void doSaveTest(BasePersistableRepository<T> repo, T orig) {
		repo.save(orig);
		T retrieved = repo.findById(orig.getId());
		assertThat(orig, is(not(nullValue())));
		assertThat(orig, is(equalTo(retrieved)));
	}
	
	protected void doNullUpdateTest(BasePersistableRepository<T> repo){
		try {
			repo.update(null);
			fail("Should have thrown IllegalArgumentException while updating with null");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
	}
	
	protected void doNullIdUpdateTest(BasePersistableRepository<T> repo, T persistable){
		try {
			repo.update(persistable);
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}
	}
	
	protected void doNullFieldUpdateTest(BasePersistableRepository<T> repo, T persistable){
		try {
			repo.update(persistable);
			fail("Should have thrown DataAccessException while saving null to non-null column");
		} catch (Exception e) {
			assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
		}
	}
	
	protected void doUpdateTest(BasePersistableRepository<T> repo, T orig) {
		repo.update(orig);
		T retrieved = repo.findById(orig.getId());
		assertThat(orig, is(not(nullValue())));
		assertThat(orig, is(equalTo(retrieved)));
	}
	
	protected void doNullSaveAllTest(BasePersistableRepository<T> repo, Collection<T> coll) {
		try {
			repo.saveAll(coll);
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		} 
	}
	
	protected void doNullIdSaveAllTest(BasePersistableRepository<T> repo, Collection<T> coll){
		try {
			repo.saveAll(coll);
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		} finally {
			coll.clear();
		}
	}
	
	protected void doPersistableFindByIdTest(BasePersistableRepository<T> repo, String id){
		T p = null;
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
	
	protected void doPersistableFindByNameTest(BasePersistableRepository<T> repo, String name, int nameRecordsCount, int totalCount){
		Collection<T> c = null;
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
	

	protected void doPersistableGetAllTest(BasePersistableRepository<T> repo, int totalCount) {
		Collection<T> c = repo.getAll();
		assertThat(c, is(not(nullValue())));
		assertThat(c, hasSize(totalCount));
	}
	
	protected void doPersistableDeleteAllTest(BasePersistableRepository<T> repo) {
		repo.deleteAll();
	}
	
	protected void doPersistableCountAllTest(BasePersistableRepository<T> repo, int expectedCount) {
		int actualCount = repo.countAll();
		assertThat(actualCount, is(equalTo(expectedCount)));
	}
	
	protected void doPersistableDeleteByIdTest(BasePersistableRepository<T> repo, String id){
		int origCount = repo.countAll();
		repo.deleteById(null);
		int updatedCount = repo.countAll();
		assertThat(updatedCount, is(equalTo(origCount)));
		
		repo.deleteById("");
		updatedCount = repo.countAll();
		assertThat(updatedCount, is(equalTo(origCount)));
		
		repo.deleteById(id);
		T p = repo.findById(id);
		assertThat(p, is(nullValue()));
		updatedCount = repo.countAll();
		assertThat(updatedCount, is(equalTo(origCount-1)));
		
	}
	
	protected void doPersistableDeleteByNameTest(BasePersistableRepository<T> repo, String name){
		int origCount = repo.countAll();
		repo.deleteByName(null);
		int updatedCount = repo.countAll();
		assertThat(updatedCount, is(equalTo(origCount)));
		
		repo.deleteByName("");
		updatedCount = repo.countAll();
		assertThat(updatedCount, is(equalTo(origCount)));
		
		repo.deleteByName(name);
		updatedCount = repo.countAll();
		assertThat(updatedCount, is(equalTo(origCount-1)));
		
	}
	
}
