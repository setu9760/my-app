package spring.desai.webconsole.controllers.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.desai.common.model.Persistable;
import spring.desai.common.model.Response;
import spring.desai.common.model.dto.DTO;
import spring.desai.common.model.dto.DTOFactory;
import spring.desai.common.model.dto.PaymentDTO;
import spring.desai.common.model.dto.StudentDTO;
import spring.desai.common.model.dto.SubjectDTO;
import spring.desai.common.model.dto.TutorDTO;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.SchoolService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RestController {

	@Autowired
	private ReadOnlyService readOnlyService;
	@Autowired
	private SchoolService schoolService;
	private DTOFactory dtoFactory = DTOFactory.getInstance();
	private static final String OP_SUCCESS = "Operation OK";

	@GetMapping("/student/all")
	public ResponseEntity<Response<Collection<StudentDTO>>> getAllStudents() throws Exception {
		return prepareResponse(dtoFactory.fromstudents(readOnlyService.getAllStudents()));
	}

	@GetMapping("/subject/all")
	public ResponseEntity<Response<Collection<SubjectDTO>>> getAllSubjects() throws Exception {
		return prepareResponse(dtoFactory.fromSubjects(readOnlyService.getAllSubjects()));
	}

	@GetMapping("/tutor/all")
	public ResponseEntity<Response<Collection<TutorDTO>>> getAllTutors() throws Exception {
		return prepareResponse(dtoFactory.fromTutors(readOnlyService.getAllTutors()));
	}

	@GetMapping(value = "/{entity}/{id}")
	public ResponseEntity<Response<DTO>> retrieveEntity(@PathVariable String entity, @PathVariable String id) {
		Persistable p = null;
		switch (entity.toLowerCase()) {
		case "student":
			p = readOnlyService.getStudentById(id);
			break;
		case "subject":
			p = readOnlyService.getSubjectById(id);
			break;
		case "role":
			p = readOnlyService.getRole(id);
			break;
		case "payment":
			p = readOnlyService.getPaymentById(id);
			break;
		case "tutor":
			p = readOnlyService.getTutorById(id);
			break;
		}
		return prepareResponse(dtoFactory.fromPersistable(p));
	}

	@PreAuthorize("#studentId == #studentDto.id")
	@PutMapping(value = "/student/{studentId}/update")
	public ResponseEntity<Response<StudentDTO>> updateStudent(@RequestBody StudentDTO studentDto, @PathVariable String studentId)
			throws Exception {
		schoolService.update(dtoFactory.fromStudentDTO(studentDto));
		return prepareResponse(dtoFactory.fromStudent(readOnlyService.getStudentById(studentId)));
	}

	@PreAuthorize("#studentId == #studentDto.id")
	@PostMapping(value = "/student/{studentId}/create")
	public ResponseEntity<Response<StudentDTO>> createStudent(@RequestBody StudentDTO studentDto, @PathVariable String studentId)
			throws Exception {
		schoolService.save(dtoFactory.fromStudentDTO(studentDto));
		return prepareResponse(dtoFactory.fromStudent(readOnlyService.getStudentById(studentDto.getId())));
	}

	@PreAuthorize("(#studentId == #paymentDTO.id)")
	@PostMapping(value = "/student/{studentId}/pay")
	public ResponseEntity<Response<StudentDTO>> makePayment(@RequestBody PaymentDTO paymentDTO, @PathVariable String studentId)
			throws Exception {
		schoolService.makePayment(dtoFactory.fromPaymentDTO(paymentDTO));
		return prepareResponse(dtoFactory.fromStudent(readOnlyService.getStudentById(studentId)));
	}

	@PutMapping(value = "/student/addsubject/{subjectId}")
	public ResponseEntity<Response<StudentDTO>> addToSubject(@RequestBody StudentDTO student, @PathVariable String subjectId) {
		schoolService.addToSubject(student.getId(), subjectId);
		return prepareResponse(dtoFactory.fromStudent(readOnlyService.getStudentById(student.getId())));
	}

	@PutMapping(value = "/student/removesubject/{subjectId}")
	public ResponseEntity<Response<StudentDTO>> removeFromSubject(@RequestBody StudentDTO student, @PathVariable String subjectId) {
		schoolService.removeFromSubject(student.getId(), subjectId);
		return prepareResponse(dtoFactory.fromStudent(readOnlyService.getStudentById(student.getId())));
	}

	@DeleteMapping(value = "/student/{studentId}/delete")
	public ResponseEntity<Void> justTest(@PathVariable String studentId) {
		//there is never actually a full delete just deactivate to preserve records
		schoolService.deactivateStudent(studentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/subject/{subjectId}/create")
	public ResponseEntity<Response<SubjectDTO>> createSubject(@PathVariable String subjectId, @RequestBody SubjectDTO subjectDto){
		// TODO
		return null;
	}
	
	

	private <T> ResponseEntity<Response<T>> prepareResponse(T obj) {
		if (obj != null)
			return new ResponseEntity<>(new Response<>(OP_SUCCESS, obj), HttpStatus.OK);
		else
			return new ResponseEntity<>(new Response<>(HttpStatus.NOT_FOUND.getReasonPhrase(), obj), HttpStatus.NOT_FOUND);
	}
}
