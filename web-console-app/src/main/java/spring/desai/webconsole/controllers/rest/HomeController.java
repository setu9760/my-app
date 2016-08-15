package spring.desai.webconsole.controllers.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.desai.common.model.Student;
import spring.desai.common.model.dto.DTOFactory;
import spring.desai.common.model.dto.StudentDTO;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.StudentAdminService;

@RestController
@RequestMapping(value = "/rest", produces = "application/json;charset=UTF-8")
public class HomeController {

	@Autowired
	private ReadOnlyService readOnlyService;

	@Autowired
	private StudentAdminService studentAdminService;

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> getstudentDetails(@PathVariable String id) throws Exception {
		Student s = readOnlyService.getStudentById(id);
		StudentDTO dto = DTOFactory.getInstance().fromStudent(s);
		return prepareResponse(dto);
	}

	@RequestMapping(value = "/student/all", method = RequestMethod.GET)
	public ResponseEntity<Collection<StudentDTO>> getAllStudents() throws Exception {
		return prepareResponse(DTOFactory.getInstance().fromstudents(readOnlyService.getAllStudents()));
//		return new ResponseEntity<Collection<StudentDTO>>(DTOFactory.getInstance().fromstudents(readOnlyService.getAllStudents()), HttpStatus.OK);
	}

	@RequestMapping(value = "/student/update", method = RequestMethod.POST)
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDto) throws Exception{
		studentAdminService.update(DTOFactory.getInstance().fromStudentDTO(studentDto));
		Student s = readOnlyService.getStudentById(studentDto.getId());
		StudentDTO dto = DTOFactory.getInstance().fromStudent(s);
		return prepareResponse(dto);
	}
	
	@RequestMapping(value = "/student/save", method = RequestMethod.POST)
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDto) throws Exception{
		studentAdminService.save(DTOFactory.getInstance().fromStudentDTO(studentDto));
		Student s = readOnlyService.getStudentById(studentDto.getId());
		StudentDTO dto = DTOFactory.getInstance().fromStudent(s);
		return prepareResponse(dto);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> justTest(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private <T> ResponseEntity<T> prepareResponse(T obj) {
		if (obj != null)
			return new ResponseEntity<>(obj, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
