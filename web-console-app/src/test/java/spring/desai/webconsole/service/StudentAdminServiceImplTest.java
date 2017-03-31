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

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import spring.desai.common.model.Payment;
import spring.desai.common.model.Scholarship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.StudentAdminService;
import spring.desai.common.utils.I18N;

@ActiveProfiles(profiles = { "jdbc" })
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = { "classpath:/test-application-context.xml", "classpath:/test-services-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class StudentAdminServiceImplTest {

	@Autowired private StudentAdminService studentAdminService;
	
	@Autowired private ReadOnlyService readOnlyService;
	
	@Value("classpath:sql/drop-ddl.sql")
	private Resource drop_ddl;
	@Value("classpath:sql/ddl.sql")
	private Resource ddl;
	@Value("classpath:sql/dml.sql")
	private Resource dml;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void setUp() throws Exception {
			System.out.println("Refreshing database with database populator");
			ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
			populator.setContinueOnError(true);
			populator.addScripts(drop_ddl, ddl, dml);
			DatabasePopulatorUtils.execute(populator, dataSource);
	}
	
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
		Collection<Student> c = null;
		try {
			studentAdminService.saveAll(c);
		} catch (Exception e) {
			assertThat(e, is(instanceOf(IllegalArgumentException.class)));
		}
		c = new ArrayList<>();
		studentAdminService.saveAll(c);
		
		for (int i = 0; i < 3; i++) {
			c.add(new Student("TEST_ID" +i,"f","n",25,"a"));
		}
		studentAdminService.saveAll(c);
		assertThat(readOnlyService.getStudentById("TEST_ID1"), is(not(nullValue())));
		assertThat(readOnlyService.getAllStudents(), hasSize(8));
		c.add(new Student("TEST_ID" +1,"f","n",25,"a"));
		try {
			studentAdminService.saveAll(c);
		} catch (Exception e) {
			assertThat(e.getCause(), is(instanceOf(DuplicateKeyException.class)));
		}
	}
	
	@Test
	public void testFull() {
		// test saving a student
		Student s = new Student("SS1", "s", "s", 34, "s");
		studentAdminService.save(s);
		
		// add to subject and test payment modification
		Subject sub = readOnlyService.getSubjectById("subjectid5");
		studentAdminService.addToSubject(s, sub);
		assertThat(readOnlyService.getTotalToPay(s.getId()), is(equalTo(800d)));
		
		// test adding to same subject again
		studentAdminService.addToSubject(s, sub);
		assertThat(readOnlyService.getTotalToPay(s.getId()), is(not(equalTo(1600d))));
		
		// make a payment
		Payment p1 = new Payment("PAY1", 100, PaymentType.CASH, s.getId(), "N/A");
		studentAdminService.makePayment(p1);
		assertThat(readOnlyService.getTotalToPay(s.getId()), is(equalTo(700d)));
		
		// amend the payment for amount
		Payment up = readOnlyService.getPaymentById("PAY1");
		up.setAmount(200);
		studentAdminService.amendPayment(up);
		assertThat(readOnlyService.getTotalToPay(s.getId()), is(equalTo(600d)));
		
		// award scholarship
		Scholarship schol = new Scholarship("SCH1", ScholorshipType.STATE_PART, 1000, 1000, true, false, "Provided by ABCD", s.getId());
		studentAdminService.awardScholorship(schol);
		assertThat(readOnlyService.getTotalToPay(s.getId()), is(equalTo(-400d)));
		
		// issue refund
		Payment p2 = new Payment("PAY2", -100d, PaymentType.REFUND_CASH, s.getId(), "N/A");
		studentAdminService.issueRefund(p2);
		assertThat(readOnlyService.getTotalToPay(s.getId()), is(equalTo(-300d)));
		
		// add to another subject
		sub = readOnlyService.getSubjectById("subjectid4");
		studentAdminService.addToSubject(s, sub);
		assertThat(readOnlyService.getTotalToPay(s.getId()), is(equalTo(700d)));
		
		// remove from subject
		studentAdminService.removeFromSubject(s, readOnlyService.getSubjectById("subjectid5"));
		assertThat(readOnlyService.getTotalToPay(s.getId()), is(equalTo(-100d)));
		
		try {
			studentAdminService.makePayment(new Payment("PAY3", 13400d, PaymentType.CASH, s.getId(), "N/A"));
			fail("should have failed with IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Ignroe as this indicates test success
		}
	}

//	@Test
//	public void testUpdate() {
//		studentAdminService.update(null);
//	}
//
//	@Test
//	public void testUpdateAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddToSubject() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveFromSubject() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMakePayment() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAwardScholorship() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAmendPayment() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAmendScholorship() {
//		fail("Not yet implemented");
//	}

}
