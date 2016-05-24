package spring.desai.common.repository.tests;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.hamcrest.core.StringStartsWith;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import spring.desai.common.model.pojo.Student;
import spring.desai.common.repository.StudentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
public class StudentRepositoryImplTest {

	@Rule
	public ExpectedException expectedExeception = ExpectedException.none();
	
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
	public void testNullSave() {
		expectedExeception.expect(IllegalArgumentException.class);
		expectedExeception.expectMessage("Null arguments passed");
		studentRepository.save(null);
		
//		studentRepository.save(new Student("", "", 234));
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
	public void testSaveAll() throws Exception {

//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(UUID.randomUUID().toString());	
		final int count = 5;
		final CountDownLatch awaitlatch = new CountDownLatch(count);
		final CountDownLatch cdl = new CountDownLatch(1);
		for (int i = 0; i < count; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						cdl.await();
						System.out.println(/*Thread.currentThread().getName() + " - " + */UUID.randomUUID().getMostSignificantBits()/*toString()*/);
						awaitlatch.countDown();
					} catch (Exception e) {
 						e.printStackTrace();
					}
					
					
				}
			}, "Thread-"+i).start();
		}
		
		cdl.countDown();
		awaitlatch.await();
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
