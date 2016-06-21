package spring.desai.common.repository.tests.jpa;

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

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Cost;
import spring.desai.common.model.pojo.Payment;
import spring.desai.common.model.pojo.Student;
import spring.desai.common.model.pojo.Subject;
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
	public void doTest(){
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
//		Student s = studentRepository.findById("studentid5");
//		System.out.println(s);
//		System.out.println();
		
	}
	
}
