package spring.desai.webconsole.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.desai.common.model.Student;
import spring.desai.common.repository.CostCodeRepository;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.ScholarshipRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.StudentTotalToPayRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.TutorRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.SchoolService;
import spring.desai.common.service.exception.ServiceException;
import spring.desai.webconsole.interceptors.ServiceExceptionInterceptor;
import spring.desai.webconsole.service.impl.SchoolServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class SchoolServiceTest {

	@Mock private StudentRepository studentRepository;
	@Mock private SubjectRepository subjectRepository;
	@Mock private TutorRepository tutorRepository;
	@Mock private PaymentRepository paymentRepository;
	@Mock private ScholarshipRepository scholarshipRepository;
	@Mock private RoleRepository roleRepository;
	@Mock private CostCodeRepository costCodeRepository;
	@Mock private StudentTotalToPayRepository studentTotalToPayRepository;
	
	private SchoolService schoolService;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		schoolService = new SchoolServiceImpl(studentRepository, subjectRepository, tutorRepository, paymentRepository, scholarshipRepository, roleRepository, costCodeRepository, studentTotalToPayRepository);
		AspectJProxyFactory factory = new AspectJProxyFactory(schoolService);
		ServiceExceptionInterceptor interceptor = new ServiceExceptionInterceptor();
		factory.addAspect(interceptor);
		schoolService = factory.getProxy();
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullAddToSubject() throws Exception {
		schoolService.addToSubject(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNulldeactivateStudent() throws Exception {
		schoolService.deactivateStudent(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullIssueRefund() throws Exception {
		schoolService.issueRefund(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullSave() throws Exception {
		schoolService.save(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullSaveAll() throws Exception {
		schoolService.saveAll(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullUpdate() throws Exception {
		schoolService.update(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullUpdateAll() throws Exception {
		schoolService.updateAll(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullMakePayment() throws Exception {
		schoolService.makePayment(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullAmendPayment() throws Exception {
		schoolService.amendPayment(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullAwardScholorship() throws Exception {
		schoolService.awardScholorship(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullAmendScholorship() throws Exception {
		schoolService.amendScholorship(null);
	}
	
	@Test(expected = ServiceException.class)
	public void testDbException() throws Exception {
		doThrow(new RepositoryDataAccessException("", new DuplicateKeyException("duplicate key"))).when(studentRepository).save(any(Student.class));
		Student s = new Student("Mock_f_name", "Mock_l_name", 25);
		try {
			schoolService.save(s);
		} catch (Exception e) {
			assertThat(ExceptionUtils.getRootCause(e) instanceof DuplicateKeyException);
			assertThat(ExceptionUtils.indexOfThrowable(e, RepositoryDataAccessException.class), equalTo(-1));
			throw e;
		}
	}
	
	@Test
	public void testIt() throws Exception {		
		Student s = new Student("Mock_f_name", "Mock_l_name", 25);
		schoolService.save(s);
		verify(studentRepository, times(1)).save(s);
		verify(studentTotalToPayRepository, times(1)).addDefaultTotalToPayRow(s.getId());
	}
}
