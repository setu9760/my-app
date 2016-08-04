package spring.desai.webconsole.controllers.rest;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import spring.desai.common.model.Student;
import spring.desai.common.model.dto.DTOFactory;
import spring.desai.common.model.dto.ExceptionDTO;
import spring.desai.common.model.dto.StudentDTO;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.ReadOnlyService;

@RestController("/rest")
public class HomeController {

//	@Autowired
//	AdminUserMaintananceService adminService;
	
	@Autowired
	ReadOnlyService readOnlyService;
	
	@RequestMapping(value = "/student/{id}")
	public StudentDTO getstudentDetails(@PathVariable String id){
		Student s = readOnlyService.getStudentById(id);
		return DTOFactory.getInstance().fromStudent(s);
	}
	
	@ExceptionHandler(value= RepositoryDataAccessException.class)
	public ExceptionDTO exceptionHandler(RepositoryDataAccessException exception){
	
		return new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getLocalizedMessage(), exception.getStackTrace(), exception);
	}
	
//	@RequestMapping(value = "/")
//	public String home(){
//		return "home";
//	}
}
