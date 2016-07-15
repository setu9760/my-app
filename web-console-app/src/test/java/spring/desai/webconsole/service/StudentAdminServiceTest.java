package spring.desai.webconsole.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import spring.desai.common.model.Payment;
import spring.desai.common.model.Scholorship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.service.StudentAdminService;

@ActiveProfiles(profiles = { "jdbc" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-application-context.xml", "classpath:/test-services-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class StudentAdminServiceTest {

	@Autowired
	StudentAdminService studentAdminService;
	
	@Autowired
	SubjectRepository subjectRepository ;
	
	@Test
	public void testName() throws Exception {
		Student s = new Student("SS1", "s", "s", 34, "s");
		studentAdminService.save(s);
		
		
		Subject sub = subjectRepository.findById("subjectid5");
		studentAdminService.addToSubject(s, sub);
		
		studentAdminService.makePayment(new Payment("PAY1", 100, PaymentType.CASH, s.getId(), "N/A"));
		
		Scholorship schol = new Scholorship("SCH1", ScholorshipType.STATE_PART, 1000, 1000, true, false, "Provided by ABCD", s.getId());
		studentAdminService.awardScholorship(schol);
		
		studentAdminService.makePayment(new Payment("PAY2", -100d, PaymentType.REFUND_CASH, s.getId(), "N/A"));
		
		sub = subjectRepository.findById("subjectid4");
		studentAdminService.addToSubject(s, sub);
		
		try {
			studentAdminService.makePayment(new Payment("PAY3", 13400d, PaymentType.CASH, s.getId(), "N/A"));
			fail("should have failed with IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Ignore
		}
		
	}
	
}
