package spring.desai.webconsole.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
//@RequestMapping("api/json")
public class JsonResponseController extends BaseController {

//
//	@RequestMapping(value = "/all-students", method = RequestMethod.GET)
//	public String allStudents(Model model) {
//		jsonString = gson.toJson((List<?>) studentDao.getAll());
//		model.addAttribute("jsonString", jsonString);
//		return JSON_RESPONSE;
//	}
//
//	@RequestMapping(value = "/all-tutors", method = RequestMethod.GET)
//	public String allTutors(Model model) {
//		jsonString = gson.toJson((List<?>) tutorDao.getAll());
//		model.addAttribute("jsonString", jsonString);
//		return JSON_RESPONSE;
//	}
//
//	@RequestMapping(value = "/all-subjects", method = RequestMethod.GET)
//	public String allSubjects(Model model) {
//		jsonString = gson.toJson((List<?>) subjectDao.getAll());
//		model.addAttribute("jsonString", jsonString);
//		return JSON_RESPONSE;
//	}
//
//	// TODO
//	@RequestMapping(value = "/student-by-id", method = RequestMethod.POST)
//	public String searchStudentById(Model model) {
//		jsonString = gson.toJson((List<?>) studentDao.findById("0"));
//		model.addAttribute("jsonString", jsonString);
//		return JSON_RESPONSE;
//	}
//
//	// TODO
//	@RequestMapping(value = "/subject-by-id", method = RequestMethod.POST)
//	public String searchSubjectById() {
//
//		return JSON_RESPONSE;
//	}
//
//	// TODO
//	@RequestMapping(value = "/tutor-by-id", method = RequestMethod.POST)
//	public String searchTutorById() {
//
//		return JSON_RESPONSE;
//	}
//	
//
//	@Override
//	void initBinder(WebDataBinder binder) {
//		// NO-OP
//	}
}
