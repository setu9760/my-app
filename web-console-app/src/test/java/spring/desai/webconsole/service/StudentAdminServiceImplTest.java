package spring.desai.webconsole.service;

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
import static org.mockito.Matchers.notNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import spring.desai.common.model.Student;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.StudentAdminService;
import spring.desai.common.utils.I18N;

@ActiveProfiles(profiles = { "jdbc" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-application-context.xml", "classpath:/test-services-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class StudentAdminServiceImplTest {

	@Autowired private StudentAdminService studentAdminService;
	
	@Autowired private ReadOnlyService readOnlyService;
	
	@Test
	public void testSave() {
		Student s = null;
		try {
			studentAdminService.save(s);
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getString("error.null.object")));
		}
		try {
			s = new Student();
			studentAdminService.save(s);
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getString("error.null.id")));
		}
		s = new Student("TEST_ID","f","n",25,"a");
		studentAdminService.save(s);
		Student retrieved = readOnlyService.getStudentById(s.getId());
		assertThat(retrieved, is(not(nullValue())));
		if(!s.equals(retrieved))
			System.err.println("s doesnot equals retrieved");
		if(!retrieved.equals(s))
			System.err.println("retrieved doesnot equals s");
		assertThat(s, is(equalTo(retrieved)));
	}

	@Test
	public void testSaveAll() {
		try {

		} catch (Exception e) {
			
		}
		fail("Not yet implemented");
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
	public void testAddToSubject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFromSubject() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakePayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testAwardScholorship() {
		fail("Not yet implemented");
	}

	@Test
	public void testAmendPayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testAmendScholorship() {
		fail("Not yet implemented");
	}

}
