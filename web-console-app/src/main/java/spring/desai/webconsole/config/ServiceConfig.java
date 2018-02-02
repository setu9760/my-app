package spring.desai.webconsole.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import spring.desai.common.repository.CostCodeRepository;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.ScholarshipRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.StudentTotalToPayRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.TutorRepository;
import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.SchoolService;
import spring.desai.webconsole.service.impl.AdminUserMaintananceServiceImpl;
import spring.desai.webconsole.service.impl.ReadOnlyServiceImpl;
import spring.desai.webconsole.service.impl.SchoolServiceImpl;

@Configuration
// TODO below component scan can be uncommented once all the services are implemented and all of their dependencies are satisfied.
//@ComponentScan({"spring.desai.webconsole.service.impl"})
public class ServiceConfig {

	@Autowired
	protected StudentRepository studentRepository;

	@Autowired
	protected SubjectRepository subjectRepository;

	@Autowired
	protected TutorRepository tutorRepository;
	
	@Autowired
	protected PaymentRepository paymentRepository;
	
	@Autowired
	protected ScholarshipRepository scholarshipRepository;
	
	@Autowired
	protected RoleRepository roleRepository;
	
	@Autowired
	protected CostCodeRepository costCodeRepository;
	
	@Autowired
	protected StudentTotalToPayRepository studentTotalToPayRepository;
	
	@Bean(name = "readOnlyService")
	public ReadOnlyService getReadOnlyService() {
		return new ReadOnlyServiceImpl(studentRepository, subjectRepository, tutorRepository, paymentRepository, scholarshipRepository, roleRepository, costCodeRepository, studentTotalToPayRepository);
	}

	@Bean(name = "studentAdminService")
	public SchoolService getStudentAdminService() {
		return new SchoolServiceImpl(studentRepository, subjectRepository, tutorRepository, paymentRepository, scholarshipRepository, roleRepository, costCodeRepository, studentTotalToPayRepository);
	}
	
	@Bean(name = "adminUserMaintananceService")
	public AdminUserMaintananceService getAdminUserMaintananceService(){
		return new AdminUserMaintananceServiceImpl();
	}

}
