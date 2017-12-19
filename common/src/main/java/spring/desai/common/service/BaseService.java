package spring.desai.common.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Autowired
	@Qualifier("studentRepository")
	protected StudentRepository studentRepository;

	@Autowired
	@Qualifier("subjectRepository")
	protected SubjectRepository subjectRepository;

	@Autowired
	@Qualifier("tutorRepository")
	protected TutorRepository tutorRepository;
	
	@Autowired
	@Qualifier("paymentRepository")
	protected PaymentRepository paymentRepository;
	
	@Autowired
	@Qualifier("scholarshipRepository")
	protected ScholarshipRepository scholarshipRepository;
	
	@Autowired
	@Qualifier("roleRepository")
	protected RoleRepository roleRepository;
	
	@Autowired
	@Qualifier("costCodeRepository")
	protected CostCodeRepository costCodeRepository;
	
	@Autowired
	protected StudentTotalToPayRepository studentTotalToPayRepository;
	
	protected final Logger log = Logger.getLogger(getClass());
	
	protected void notNull(Object  o) {
		Assert.notNull(o, I18N.getString("error.null.object"));
	}
}
