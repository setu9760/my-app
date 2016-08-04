package spring.desai.webconsole.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.desai.common.model.Student;
import spring.desai.common.model.dto.DTOFactory;
import spring.desai.common.model.dto.ExceptionDTO;
import spring.desai.common.model.dto.StudentDTO;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.StudentAdminService;

@RestController("/rest")
public class HomeController {

	@Autowired
	private ReadOnlyService readOnlyService;

	@Autowired
	private StudentAdminService studentAdminService;
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getstudentDetails(@PathVariable String id) {
		Student s = readOnlyService.getStudentById(id);
		StudentDTO dto = DTOFactory.getInstance().fromStudent(s);
		return prepareResponse(dto);
	}

	@ExceptionHandler(value = RepositoryDataAccessException.class)
	public ExceptionDTO exceptionHandler(RepositoryDataAccessException exception) {
		// TODO ExceptionDTO logic is flawed. This returns all the attributes in json format. 
		return new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getLocalizedMessage(),
				exception.getStackTrace(), exception);
	}

	private ResponseEntity<Object> prepareResponse(Object obj){
		if (obj != null)
			return new ResponseEntity<>(obj, HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
