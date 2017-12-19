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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import spring.desai.common.repository.StudentTotalToPayRepository;
import spring.desai.common.utils.I18N;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@ActiveProfiles(profiles = { "jdbc" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcTestConfigs.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class StudentTotalPayRepositoryImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Autowired
	@Qualifier("studentTotalToPayRepository")
	private StudentTotalToPayRepository studentTotalPayRepository;

	@Test
	public void testUpdateTotalPayBy() {
		double newTotalToPay = studentTotalPayRepository.getCurrentTotalToPay(null);
		assertThat(newTotalToPay, is(equalTo(0d)));
		
		newTotalToPay = studentTotalPayRepository.getCurrentTotalToPay("studentid1");
		assertThat(newTotalToPay, is(equalTo(0d)));
		
		try {
			newTotalToPay = studentTotalPayRepository.updateTotalToPayBy(null, 500);
			fail("Should have failed with IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.id"))));
		}
		studentTotalPayRepository.addDefaultTotalToPayRow("studentid1");
		newTotalToPay = studentTotalPayRepository.updateTotalToPayBy("studentid1", 500d);
		assertThat(newTotalToPay, is(equalTo(500d)));
		
		newTotalToPay = studentTotalPayRepository.updateTotalToPayBy("studentid1", -200d);
		assertThat(newTotalToPay, is(equalTo(-200d)));
	}

}
