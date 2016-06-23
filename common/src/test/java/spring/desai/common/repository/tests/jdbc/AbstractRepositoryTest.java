package spring.desai.common.repository.tests.jdbc;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import spring.desai.common.model.Persistable;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.I18N;

@Ignore
@ActiveProfiles(profiles = { "jdbc" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public abstract class  AbstractRepositoryTest<T extends Persistable> {

	public abstract Class<T> getPersistableClazz();
	public abstract BasePersistableRepository<T> getRepo();
	protected boolean doNullFieldTest() {
		return true;
	}
	
	@Value("classpath:sql/drop-ddl.sql")
	private Resource drop_ddl;
	@Value("classpath:sql/ddl.sql")
	private Resource ddl;
	@Value("classpath:sql/dml.sql")
	private Resource dml;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void setUp() throws Exception {
		if(doRefereshDbBetweenTests()){
			System.out.println("Refreshing database with database populator");
			ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
			populator.setContinueOnError(true);
			populator.addScripts(drop_ddl, ddl, dml);
			DatabasePopulatorUtils.execute(populator, dataSource);
		}
	}
	
	protected boolean doRefereshDbBetweenTests(){
		return false;
	}

	protected void doSaveTest(T persistable){
		try {
			getRepo().save(null);
			fail("Should have thrown IllegalArgumentException while saving");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
		try {
			getRepo().save(mockPersistable(getPersistableClazz(), null));
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}
		
		if (doNullFieldTest()) {
			try {
				getRepo().save(mockPersistable(getPersistableClazz(), "MOCK-ID-1"));
				fail("Should have thrown DataAccessException while saving null to non-null column");
			} catch (Exception e) {
				assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
			}
		}
		
		getRepo().save(persistable);
		T retrieved = getRepo().findById(persistable.getId());
		assertThat(retrieved, is(not(nullValue())));
		assertThat(retrieved, is(equalTo(persistable)));
	}
	
	protected void doSaveAllTest(Collection<T> persistables){
		try {
			getRepo().saveAll(null);
			fail("Should have thrown IllegalArgumentException while saving");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		} 
		
		try {
			getRepo().saveAll(new ArrayList<T>());
			fail("Should have thrown IllegalArgumentException while saving");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		} 
		
		try {
			getRepo().saveAll(mockPersistables(getPersistableClazz(), null));
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		} 
		
		if (doNullFieldTest()) {
			try {
				getRepo().saveAll(mockPersistables(getPersistableClazz(), "studentid"));
				fail("Should have thrown DataAccessException while saving null to non-null column");
			} catch (Exception e) {
				assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
			}
		}
		
		int origCount = getRepo().countAll();
		getRepo().saveAll(persistables);
		int afterCount = getRepo().countAll();
		assertThat(afterCount, is(not(origCount)));
		assertThat(afterCount, is(equalTo(origCount + 5)));
	}
	
	protected void doUpdateTest(T updatedPersistable, String idToUpdate){
		try {
			getRepo().update(null);
			fail("Should have thrown IllegalArgumentException while updating with null");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
		
		try {
			getRepo().update(mockPersistable(getPersistableClazz(), null));
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}
		
		if (doNullFieldTest()) {
			try {
				getRepo().update(mockPersistable(getPersistableClazz(), idToUpdate));
				fail("Should have thrown DataAccessException while saving null to non-null column");
			} catch (Exception e) {
				assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
			}
		}
		
		T orig = getRepo().findById(updatedPersistable.getId());
		getRepo().update(updatedPersistable);
		T retrieved = getRepo().findById(updatedPersistable.getId());
		assertThat(orig, is(not(equalTo(retrieved))));
		assertThat(updatedPersistable, is(not(nullValue())));
		assertThat(updatedPersistable, is(equalTo(retrieved)));
	}
	
	protected void doUpdateAllTest(Collection<T> origPersistables, Collection<T> updatedPersistables, String idToUpdate){
		try {
			getRepo().updateAll(null);
			fail("Should have thrown IllegalArgumentException while updating with null");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
		
		try {
			getRepo().updateAll(new ArrayList<T>());
			fail("Should have thrown IllegalArgumentException while updating with null");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
		
		
		try {
			getRepo().updateAll(mockPersistables(getPersistableClazz(), null));
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		} 
		
		if (doNullFieldTest()) {
			try {
				getRepo().updateAll(mockPersistables(getPersistableClazz(), idToUpdate));
				fail("Should have thrown DataAccessException while saving null to non-null column");
			} catch (Exception e) {
				assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
			}
		}
		
		getRepo().updateAll(updatedPersistables);
		assertThat(updatedPersistables.size(), is(equalTo(origPersistables.size())));
		assertThat(updatedPersistables, is(not(equalTo(origPersistables))));
	}
	
	protected void doFindByIdTest(String id){
		T p = null;
		p = getRepo().findById(null);
		assertThat(p, is(nullValue()));
		
		p = getRepo().findById("");
		assertThat(p, is(nullValue()));
		
		p = getRepo().findById("NON_EXISTANT_ID");
		assertThat(p, is(nullValue()));
		
		p = getRepo().findById(id);
		assertThat(p, is(not(nullValue())));
		assertThat(p.getId(), is(equalTo(id)));
	}
	
	protected void doFindByNameTest(String name, int nameRecordsCount, int totalCount){
		Collection<T> c = null;
		c = getRepo().findByName(null);
		assertThat(c, is(not(nullValue())));
		assertThat(c, is(empty()));
		
		c = getRepo().findByName("NON_EXISTANT_NAME");
		assertThat(c, is(not(nullValue())));
		assertThat(c, is(empty()));
		
		c = getRepo().findByName(name);
		assertThat(c, hasSize(nameRecordsCount));
		
		c = getRepo().findByName(name.toLowerCase());
		assertThat(c, hasSize(nameRecordsCount));
		
		c = getRepo().findByName(name.toUpperCase());
		assertThat(c, hasSize(nameRecordsCount));
		
		c = getRepo().findByName("");
		assertThat(c, is(not(nullValue())));
		assertThat(c, hasSize(totalCount));
	}
	

	protected void doGetAllTest(int totalCount) {
		Collection<T> c = getRepo().getAll();
		assertThat(c, is(not(nullValue())));
		assertThat(c, hasSize(totalCount));
	}
	
	protected void doDeleteAllTest() {
		getRepo().deleteAll();
		assertThat(getRepo().countAll(), is(equalTo(0)));
	}
	
	protected void doCountAllTest(int expectedCount) {
		int actualCount = getRepo().countAll();
		assertThat(actualCount, is(equalTo(expectedCount)));
	}
	
	protected void doDeleteByIdTest(String id){
		int origCount = getRepo().countAll();
		getRepo().deleteById(null);
		int updatedCount = getRepo().countAll();
		assertThat(updatedCount, is(equalTo(origCount)));
		
		getRepo().deleteById("");
		updatedCount = getRepo().countAll();
		assertThat(updatedCount, is(equalTo(origCount)));
		
		getRepo().deleteById(id);
		T p = getRepo().findById(id);
		assertThat(p, is(nullValue()));
		updatedCount = getRepo().countAll();
		assertThat(updatedCount, is(equalTo(origCount-1)));
		
	}
	
	protected void doDeleteByNameTest(String name){
		int origCount = getRepo().countAll();
		getRepo().deleteByName(null);
		int updatedCount = getRepo().countAll();
		assertThat(updatedCount, is(equalTo(origCount)));
		
		getRepo().deleteByName("");
		updatedCount = getRepo().countAll();
		assertThat(updatedCount, is(equalTo(origCount)));
		
		getRepo().deleteByName(name);
		updatedCount = getRepo().countAll();
		assertThat(updatedCount, is(equalTo(origCount-1)));
		
	}
	
	protected T mockPersistable(Class<T> clazzToMock, String idToReturn){
		T t = mock(clazzToMock);
		when(t.getId()).thenReturn(idToReturn);
		return t;
	}
	
	protected Collection<T> mockPersistables(Class<T> clazzToMock, String idToReturn){
		List<T> l = new ArrayList<T>();
		for (int i = 0; i < 5; i++) {
			T t = mock(clazzToMock);
			if (idToReturn != null) {
				when(t.getId()).thenReturn(idToReturn + i);
			}
			l.add(t);
		}
		return l;
	}
	
	protected void assertNotNull(T t){
		assertThat(t, is(not(nullValue())));
	}
	
	protected void assertNotNull(Collection<T> c){
		assertThat(c, is(not(nullValue())));
		assertThat(c, is(empty()));
	}
	
	protected void assertSize(Collection<?> c, int size){
		assertThat(c, is(not(nullValue())));
		assertThat(c.size(), is(equalTo(size)));
	}
}
