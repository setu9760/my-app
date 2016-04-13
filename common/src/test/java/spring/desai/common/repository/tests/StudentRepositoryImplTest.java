package spring.desai.common.repository.tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import junit.framework.Assert;
import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.model.pojo.Student;
import spring.desai.common.repository.StudentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
public class StudentRepositoryImplTest {

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

	@Test(expected=IllegalArgumentException.class)
	public void testNullSave() {
		studentRepository.save(null);
	}

	@Test
	public void testSave() {
		Student orig = new Student("ABCDE", "ABCDE", 20, "Address");
		studentRepository.save(orig);
		Collection<Student> coll= studentRepository.findByName("ABCDE");
		Assert.assertNotNull(coll);
		Assert.assertFalse(coll.isEmpty());
		Student retrieved = (Student) coll.toArray()[0];
		Assert.assertEquals(orig, retrieved);
		Student orig2 = new Student("ABCDE", "ABCDE", 20, "Address");
		System.out.println(orig2);
		Assert.assertEquals(orig.getId(), retrieved.getId());
		}

	@Test
	public void testSaveAll() {
		List<Student> studs = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Student s = new Student("ABCDE" + i, "ABCDE" + i, 20 + i, "Address");
			studs.add(s);
		}
		
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindbyName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
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

	@Test
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountAll() {
		fail("Not yet implemented");
	}

}
