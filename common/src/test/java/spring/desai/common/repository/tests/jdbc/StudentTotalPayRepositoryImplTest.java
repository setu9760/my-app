package spring.desai.common.repository.tests.jdbc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import spring.desai.common.repository.StudentTotalToPayRepository;
import spring.desai.common.utils.I18N;

@ActiveProfiles(profiles = { "jdbc" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class StudentTotalPayRepositoryImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Autowired
	@Qualifier("studentTotalPayRepository")
	StudentTotalToPayRepository studentTotalPayRepository;

	@Test
	public void testUpdateTotalPayBy() {
		double newTotal = studentTotalPayRepository.getCurrentTotalToPay(null);
		assertThat(newTotal, is(equalTo(0d)));
		
		newTotal = studentTotalPayRepository.getCurrentTotalToPay("studentid1");
		assertThat(newTotal, is(equalTo(0d)));
		
		try {
			newTotal = studentTotalPayRepository.updateTotalToPayBy(null, 500);
			fail("Should have failed with IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.id"))));
		}
		
		newTotal = studentTotalPayRepository.updateTotalToPayBy("studentid1", 500d);
		assertThat(newTotal, is(equalTo(500d)));
		
		newTotal = studentTotalPayRepository.updateTotalToPayBy("studentid1", -200d);
		assertThat(newTotal, is(equalTo(300d)));
		
		newTotal = studentTotalPayRepository.updateTotalToPayBy("studentid1", -500d);
		assertThat(newTotal, is(equalTo(-200d)));
		
		newTotal = studentTotalPayRepository.updateTotalToPayBy("studentid1", 500d);
		assertThat(newTotal, is(equalTo(300d)));
	}

}
