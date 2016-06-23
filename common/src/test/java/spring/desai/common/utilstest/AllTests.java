package spring.desai.common.utilstest;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

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

import spring.desai.common.model.Payment;
import spring.desai.common.model.Scholorship;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.ScholorshipRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.SubjectRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
public class AllTests {

	@Autowired
	ApplicationContext context;

	@Before
	public void beforeThis() {

	}

	@Test(expected = ConnectException.class)
	public void testThis() throws Exception {
		//
//		for (String string : context.getBeanDefinitionNames()) {
//			System.out.println(string);
//		}
		//
		throw new ConnectException();
	}

	@Test
	public void testThat() throws Exception {
//		ReadOnlyService roService = context.getBean(ReadOnlyService.class);
		// Collection students = roService.getAllStudents();
		//
		// for (Object student : students) {
		// System.out.println(student);
		// }
		 PaymentRepository pr = context.getBean(PaymentRepository.class);
		 
		 System.out.println(pr.countAll());
		 System.out.println(pr.findById("payment1"));
		 System.out.println(pr.findbyStudentId("studentid1"));
		 System.out.println(pr.findByType(PaymentType.CHEQUE));
		 Payment p = new Payment("DummyInsertPaymentID", 300d, PaymentType.BRAC);
		 p.setStud_id("studentid3");
		 pr.save(p);
		 
		 p.setStud_id("studentid2");
		 p.setAmount(300d);
		 p.setPaymentType(PaymentType.CASH);
		 pr.update(p);
		 
		 ScholorshipRepository sr = context.getBean(ScholorshipRepository.class);
		 
		 System.out.println(sr.countAll());
		 System.out.println(sr.findByType(ScholorshipType.STATE_PART));
		 System.out.println(sr.findById("schlrid1"));
		 System.out.println(sr.findByStudentId("studentid1"));
		
		 List<Scholorship> scholorships = new ArrayList<>();
		 
		 scholorships.add(new Scholorship("sch1", "", ScholorshipType.MGMT_PART, 100d, 20d, false, false, null, "studentid5"));
		 scholorships.add(new Scholorship("sch2", "", ScholorshipType.MGMT_PART, 100d, 20d, false, false, null, "studentid5"));
		 scholorships.add(new Scholorship("sch3", "", ScholorshipType.MGMT_PART, 100d, 20d, false, false, null, "studentid5"));
		 scholorships.add(new Scholorship("sch4", "", ScholorshipType.MGMT_PART, 100d, 20d, false, false, null, "studentid5"));
		 scholorships.add(new Scholorship("sch5", "", ScholorshipType.MGMT_PART, 100d, 20d, false, false, null, "studentid5"));
		 sr.saveAll(scholorships);
		 System.out.println(sr.countAll());
		 
		 SubjectRepository sbr = context.getBean(SubjectRepository.class);
		 
		 System.out.println(sbr.getSubjectsForStudentId("studentid5"));
		 
		 StudentRepository str = context.getBean(StudentRepository.class);
		 str.deleteById("studentid1");
		 
		 System.out.println(str.getStudentsForSubjectId("subject5"));
		 
		 System.out.println(sr.findByStudentId("studentid1"));
		 
//		System.out.println(roService.getPaymentById("payment1"));
//
//		System.out.println(roService.getPaymentsForStudent("studentid1"));
		// System.out.println(roService.getStudentById("studentid5"));
		//
		// System.out.println(roService.getAllSubjects());
		//
		// System.out.println(roService.getAllTutors());
		//
		// System.out.println(roService.getTutorById("tutorid1"));
	}

	@After
	public void afterThis() {

	}

}
