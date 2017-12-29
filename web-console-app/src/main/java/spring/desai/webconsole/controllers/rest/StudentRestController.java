package spring.desai.webconsole.controllers.rest;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.desai.common.model.dto.StudentDTO;

//@RestController
//@RequestMapping(value="/student", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentRestController extends BaseRestController<StudentDTO>{

	@Override
	protected Collection<StudentDTO> retrieveAllImpl() {
		return dtoFactory.fromstudents(readOnlyService.getAllStudents());
	}

	@Override
	protected StudentDTO retrieveByIdImpl(String id) {
		return dtoFactory.fromStudent(readOnlyService.getStudentById(id));
	}

	@Override
	protected StudentDTO createImpl(String id, StudentDTO dto) {
		schoolService.save(dtoFactory.fromStudentDTO(dto));
		return dtoFactory.fromStudent(readOnlyService.getStudentById(id));
	}

	@Override
	protected StudentDTO updateImpl(String id, StudentDTO dto) {
		schoolService.update(dtoFactory.fromStudentDTO(dto));
		return dtoFactory.fromStudent(readOnlyService.getStudentById(id));
	}

	@Override
	protected Void deleteImpl(String id) {

		return null;
	}
}
