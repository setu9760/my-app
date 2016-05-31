package spring.desai.common.repository.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import spring.desai.common.model.pojo.Student;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.I18N;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentRepositoryImplTest extends AbstractRepositoryTest {

	@Autowired
	ApplicationContext context;

	@Autowired
	StudentRepository studentRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {

		doNullSaveTest(studentRepository);
		Student orig = new Student("ABCDE", "ABCDE", 20, "Address");
		try {
			studentRepository.save(orig);
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}
		
		try {
			orig = new Student("ABCDS-ID-0123", null, null, 20, "Address");
			studentRepository.save(orig);
			fail("Should have thrown DataAccessException while saving null to non-null column");
		} catch (Exception e) {
			assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
		}
		
		orig = new Student("ABCDS-ID-0123", "ABCDE", "ABCDE", 20, "Address");
		studentRepository.save(orig);
		Student retrieved = studentRepository.findById(orig.getId());
		assertThat(retrieved, is(notNullValue()));
		assertThat(retrieved, is(equalTo(orig)));

		try {
			studentRepository.save(orig);
			fail("Should have thrown Exception while trying to save duplicate data.");
		} catch (RepositoryDataAccessException e) {
			assertThat(e.contains(DuplicateKeyException.class), is(true));
		}
	}

	@Test
	public void testSaveAll() throws Exception {
		
		try {
			studentRepository.saveAll(null);
			fail("Should have thrown IleegalArgumentException while saving null.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}
		List<Student> l = new ArrayList<>();
		try {
			studentRepository.saveAll(l);
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}
		
		try {
			l.add(new Student("ABCDE", "ABCDE", 25));
			studentRepository.saveAll(l);
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		} finally {
			l.clear();
		}
		
		try {
			l.add(new Student("ID2", null, null, 25, null));
			studentRepository.saveAll(l);
			fail("Should have thrown IllegalArgumentException while saving null ID field");
		} catch (RepositoryDataAccessException e) {
			assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
		} finally {
			l.clear();
		}
		
		try {
			l.add(new Student("ABCDS-ID-0123", "ABCDE1", "ABCDE1", 20, "Address"));
			l.add(new Student("ABCDS-ID-0123", "ABCDE2", "ABCDE2", 20, "Address"));
			studentRepository.saveAll(l);
			fail("Should have thrown Exception while trying to save duplicate data.");
		} catch (RepositoryDataAccessException e) {
			assertThat(e.contains(DuplicateKeyException.class), is(true));
		} finally{
			l.clear();
		}
		
		int origCount = studentRepository.countAll();
		List<Student> origStuds = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			origStuds.add(new Student("TEST-ALL-" + i, "f_name-" + i, "l_name-" + i, 25, "addr"));
		}
		studentRepository.saveAll(origStuds);
		int afterCount = studentRepository.countAll();
		assertThat(afterCount, is(not(origCount)));
		assertThat(afterCount, is(equalTo(origCount + 5)));
	}

	@Test
	public void testUpdate() {
		doNullUpdateTest(studentRepository);
		
		try {
			studentRepository.update(new Student("ABCDE", "ABCDE", 25));
			fail("Should have thrown IleegalArgumentException while updating with null ID field");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}
		
		Student original = studentRepository.findById("studentid1");
		
		try {
			original.setF_name(null);
			original.setL_name(null);
			studentRepository.update(original);
			fail("Should have thrown DataAccessException while saving null to non-null column.");
		} catch (Exception e) {
			assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
		}
		
		original.setF_name("UPDATED_NAME");
		original.setL_name("UPDATED_NAME");
		studentRepository.update(original);
		Student retrieved = studentRepository.findById("studentid1");
		assertThat(retrieved, is(notNullValue()));
		assertThat(retrieved, is(equalTo(original)));
	}
	
	@Test
	public void testUpdateAll() {
		
		try {
			studentRepository.updateAll(null);
			fail("Should have failed with IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}
		
		List<Student> l = new ArrayList<>();
		try {
			studentRepository.updateAll(l);
			fail("Should have failed with IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}
		
		try {
			l.add(new Student("F_NAME", "L_NAME", 25));
			studentRepository.updateAll(l);
			fail("Should have failed with IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		} finally{
			l.clear();
		}
		
		try {
			l.add(new Student("studentid1", null, null, 25, ""));
			studentRepository.updateAll(l);
			fail("Should have failed with DataAccessException.");
		} catch (Exception e) {
			assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
		} finally{
			l.clear();
		}
		
//		Collection<Student> origStuds = studentRepository.getAll();
//		for (Student student : origStuds) {
//			student.setF_name("UPDATED_F_NAME");
//			student.setL_name("UPDATED_L_NAME");
//		}
//		studentRepository.updateAll(origStuds);
//		
//		Collection<Student> updatedStuds = studentRepository.getAll();
//		assertThat(updatedStuds.size(), is(equalTo(origStuds.size())));
//		assertThat(updatedStuds, is(equalTo(origStuds)));
	}

	@Test
	public void testFindById() {
		testPersistableFindById(studentRepository, "studentid1");
	}
	
	@Test
	public void testFindbyName() {
		testPersistableFindByName(studentRepository, "f_name4", 2, 5);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		testPersistableGetAll(studentRepository, 5);
	}

	@Test
	public void testGetStudentsForSubjectId() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByName() {
		fail("Not yet implemented");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteAll() {
		testPersistableDeleteAll(studentRepository);
	}

	@Test
	public void testCountAll() {
		testPersistableCountAll(studentRepository, 5);
	}

}
