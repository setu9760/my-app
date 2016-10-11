package spring.desai.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.ScholarshipRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.TutorRepository;

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
	
}
