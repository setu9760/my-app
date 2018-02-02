package spring.desai.webconsole.controllers.rest;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.desai.common.model.dto.DTO;
import spring.desai.common.model.dto.DTOFactory;
import spring.desai.common.model.dto.PaymentDTO;
import spring.desai.common.model.dto.StudentDTO;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.SchoolService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RestController {

	@Autowired private ReadOnlyService readOnlyService;
	@Autowired private SchoolService schoolService;
	
	private DTOFactory dtoFactory = DTOFactory.getInstance();

	@GetMapping
	public ResponseEntity<String> restApi(){
		return new ResponseEntity<String>("{\"msg\":\"Welcome to the rest API\"}", HttpStatus.OK);
	}
	
	@GetMapping("{entity}/all")
	public ResponseEntity<?> getAll(@PathVariable String entity) throws Exception {
		return prepareResponse(dtoFactory.fromPersistables(entity, readOnlyService.getAllEntities(entity)));
	}

	@GetMapping(value = "/{entity}/{id}")
	public ResponseEntity<DTO> retrieveEntity(@PathVariable String entity, @PathVariable String id) {
		return prepareResponse(dtoFactory.fromPersistable(readOnlyService.getEntityById(entity, id)));
	}
	
	@PreAuthorize("#id == #dto.getId()")
	@PostMapping(value = "/{entity}/{id}/create")
	public ResponseEntity<DTO> create(@PathVariable String entity, @PathVariable String id, @RequestBody @Valid DTO dto, BindingResult result) {
		schoolService.save(dtoFactory.fromDTO(dto));
		return prepareResponse(dtoFactory.fromPersistable(readOnlyService.getEntityById(entity, id)));
	}

	@DeleteMapping(value = "/{entity}/{id}/delete")
	public ResponseEntity<Void> justTest(@PathVariable String id) {
		// TODO 
		//there is never actually a full delete just deactivate to preserve records
		schoolService.deactivateStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("#id == #dto.getId()")
	@PutMapping(value = "/{entity}/{id}/update")
	public ResponseEntity<DTO> update(@PathVariable String entity, @RequestBody @Valid DTO dto, @PathVariable String id) {
		schoolService.update(dtoFactory.fromDTO(dto));
		return prepareResponse(dtoFactory.fromPersistable(readOnlyService.getEntityById(entity, id)));
	}

	@PreAuthorize("#studentId == #paymentDTO.id")
	@PostMapping(value = "/student/{studentId}/pay")
	public ResponseEntity<StudentDTO> makePayment(@RequestBody PaymentDTO paymentDTO, @PathVariable String studentId)
			throws Exception {
		schoolService.makePayment(dtoFactory.fromPaymentDTO(paymentDTO));
		return prepareResponse(dtoFactory.fromStudent(readOnlyService.getStudentById(studentId)));
	}

	@PutMapping(value = "/student/addsubject/{subjectId}")
	public ResponseEntity<StudentDTO> addToSubject(@RequestBody StudentDTO student, @PathVariable String subjectId) {
		schoolService.addToSubject(student.getId(), subjectId);
		return prepareResponse(dtoFactory.fromStudent(readOnlyService.getStudentById(student.getId())));
	}

	@PutMapping(value = "/student/removesubject/{subjectId}")
	public ResponseEntity<StudentDTO> removeFromSubject(@RequestBody StudentDTO student, @PathVariable String subjectId) {
		schoolService.removeFromSubject(student.getId(), subjectId);
		return prepareResponse(dtoFactory.fromStudent(readOnlyService.getStudentById(student.getId())));
	}


	private <T> ResponseEntity<Collection<T>> prepareResponse(Collection<T> obj) {
		if (obj != null)
			return new ResponseEntity<>(obj, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	private <T> ResponseEntity<T> prepareResponse(T obj) {
		if (obj != null)
			return new ResponseEntity<>(obj, HttpStatus.OK);
		else {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
			return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
		}
	}
}
