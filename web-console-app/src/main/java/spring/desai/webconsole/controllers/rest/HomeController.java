package spring.desai.webconsole.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.desai.common.model.Student;
import spring.desai.common.model.dto.DTOFactory;
import spring.desai.common.model.dto.StudentDTO;
import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.ReadOnlyService;

@RestController("/rest")
public class HomeController {

	@Autowired
	AdminUserMaintananceService adminService;
	
	@Autowired
	ReadOnlyService readOnlyService;
	
	@RequestMapping(name = "/student/{id}")
	public StudentDTO getstudentDetails(@PathVariable String id){
		Student s = readOnlyService.getStudentById(id);
		return DTOFactory.getInstance().fromStudent(s);
	}
}
