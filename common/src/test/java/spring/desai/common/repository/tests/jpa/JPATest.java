package spring.desai.common.repository.tests.jpa;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import spring.desai.common.model.Cost;
import spring.desai.common.model.Payment;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.repository.CostCodeRepository;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.ScholorshipRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.TutorRepository;

@ActiveProfiles(profiles = { "jpa" })
@Transactional(noRollbackForClassName="")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class JPATest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	CostCodeRepository costCodeRepository;
	
	@Autowired 
	PaymentRepository paymentRepository;
	
	@Autowired
	ScholorshipRepository scholorshipRepository;
	
	@Autowired
	TutorRepository tutorRepository;
	
	@Test
	@Transactional
	public void doTest() throws Exception{
//		costCodeRepository.save(new Cost("SOME_COSE", 1234));
//		
//		Cost c = costCodeRepository.findById("SOME_COSE");
//		System.out.println(c);
//		c.setAmount(123213);
//		costCodeRepository.update(c);
////		
		Subject sb = subjectRepository.findById("subjectid1");
//		System.out.println(sb);
		
		Student s3 = new Student("IDDD", "S", "S", 34, "S");
		studentRepository.save(s3);
		
		s3 = studentRepository.findById("IDDD");
		System.out.println(s3);
		s3.addSubject(sb); 
		studentRepository.update(s3);
		s3 = studentRepository.findById("IDDD");
		System.out.println(s3);
		
		sb = subjectRepository.findById("subjectid2");
		s3.addSubject(sb);
		studentRepository.update(s3);
		s3 = studentRepository.findById("IDDD");
		System.out.println(s3);
		
		 sb = subjectRepository.findById("subjectid3");
		s3.addSubject(sb);
		studentRepository.update(s3);
		s3 = studentRepository.findById("IDDD");
		System.out.println(s3);
		
		System.out.println(tutorRepository.findById("tutorid7"));
		
		Payment p = new Payment("IDD", 32434, PaymentType.CASH, "IDDD");
		paymentRepository.save(p);
		
		System.out.println(paymentRepository.findById("IDD"));
		
		Thread.sleep(2000);
		p.setComments("UPDATED Comments");
		p.setAmount(400);
		p.setPaymentDateTime(DateTime.now());
		paymentRepository.update(p);
		System.out.println(paymentRepository.findById("IDD"));
//		Student s = studentRepository.findById("studentid5");
//		System.out.println(s);
//		System.out.println();
		
	}
	
}
