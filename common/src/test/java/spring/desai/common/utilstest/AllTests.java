package spring.desai.common.utilstest;

import java.net.ConnectException;
import java.util.Collection;

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

import spring.desai.common.model.pojo.Student;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.service.ReadOnlyService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(value = "classpath:test-application-context.xml")
public class AllTests {

	@Autowired
	ApplicationContext context;

	@Before
	public void beforeThis() {

	}

	@Test(expected = ConnectException.class)
	public void testThis() throws Exception{
//		
		for (String string : context.getBeanDefinitionNames()) {
			System.out.println(string);
		}
//		
		throw new ConnectException();
	}
	
	@Test
	public void testThat() throws Exception {
		ReadOnlyService roService = context.getBean(ReadOnlyService.class);
//		Collection students = roService.getAllStudents();
//		
//		for (Object student : students) {
//			System.out.println(student);
//		}
//		PaymentRepository repository = context.getBean(PaymentRepository.class);
		System.out.println(roService.getPaymentById("payment1"));
		
		System.out.println(roService.getPaymentsForStudent("studentid1"));
//		System.out.println(roService.getStudentById("studentid5"));
//		
//		System.out.println(roService.getAllSubjects());
//		
//		System.out.println(roService.getAllTutors());
//		
//		System.out.println(roService.getTutorById("tutorid1"));
	}

	@After
	public void afterThis() {

	}

}
