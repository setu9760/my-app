package spring.desai.common.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import spring.desai.common.repository.CostCodeRepository;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.ScholarshipRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.StudentTotalToPayRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.TutorRepository;
import spring.desai.common.utils.I18N;

public abstract class BaseService {

	protected StudentRepository studentRepository;
	protected SubjectRepository subjectRepository;
	protected TutorRepository tutorRepository;
	protected PaymentRepository paymentRepository;
	protected ScholarshipRepository scholarshipRepository;
	protected RoleRepository roleRepository;
	protected CostCodeRepository costCodeRepository;
	protected StudentTotalToPayRepository studentTotalToPayRepository;
	
	protected final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	public BaseService(StudentRepository studentRepository, SubjectRepository subjectRepository,
			TutorRepository tutorRepository, PaymentRepository paymentRepository,
			ScholarshipRepository scholarshipRepository, RoleRepository roleRepository,
			CostCodeRepository costCodeRepository, StudentTotalToPayRepository studentTotalToPayRepository) {
		super();
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.tutorRepository = tutorRepository;
		this.paymentRepository = paymentRepository;
		this.scholarshipRepository = scholarshipRepository;
		this.roleRepository = roleRepository;
		this.costCodeRepository = costCodeRepository;
		this.studentTotalToPayRepository = studentTotalToPayRepository;
	}

	protected void notNull(Object  o) {
		Assert.notNull(o, I18N.getString("error.null.object"));
	}
}
