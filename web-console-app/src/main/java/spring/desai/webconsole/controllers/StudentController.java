package spring.desai.webconsole.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
//@RequestMapping("/student")
public class StudentController extends BaseController {
//
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.setValidator(studentValidator);
//	}
//
//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public String studentAll(Model model) {
//		List<Student> students = (List<Student>) studentDao.getAll();
//		model.addAttribute("serverTime", getformattedDate());
//		model.addAttribute("students", students);
//		model.addAttribute("title", "All Students");
//		logger.info("studentAll handler");
//		return "all";
//	}
//
//	@RequestMapping(value = "/insert", method = RequestMethod.GET)
//	public String studentInsertForm(Model model) throws GuidGeneratorException {
//
//		Student student = new Student();
//		model.addAttribute("serverTime", getformattedDate());
//		model.addAttribute("student", student);
//		model.addAttribute("title", "Insert Student");
//		return "insert";
//	}
//
//	@RequestMapping(value = "/insert", method = RequestMethod.POST)
//	public String studentInsertResult(@ModelAttribute("student") Student student, Model model, BindingResult bindingResult) {
//		logger.info("studentInsert post request handler");
//		studentValidator.validate(student, bindingResult);
//		model.addAttribute("serverTime", getformattedDate());
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("message", "there was an error completing the request");
//			model.addAttribute("title", "Error");
//			return "insert";
//		} else {
//			studentDao.insert(student);
//			model.addAttribute("message", "Successfully saved student: " + student);
//			model.addAttribute("title", "Success");
//			return "result";
//		}
//	}
//
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public String studentSearchForm(Model model) throws GuidGeneratorException {
//		logger.info("studentSearch get request handler");
//		Student student = new Student();
//		model.addAttribute("serverTime", getformattedDate());
//		model.addAttribute("student", student);
//		model.addAttribute("title", "Search student");
//		return "student-search";
//	}
//
//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public String studentSearchResult(Student student, Model model, BindingResult result) {
//		logger.info("studentSearch post request handler");
//		model.addAttribute("serverTime", getformattedDate());
//		if (result.hasErrors()) {
//			model.addAttribute("message", "there was an error completing the request");
//			model.addAttribute("title", "Error");
//			return ("student-search");
//		} else {
//			Object obj = studentDao.findById(student.getId());
//			if (obj != null) {
//				logger.info(obj.toString());
//				model.addAttribute("message", "student found: " + obj);
//				model.addAttribute("title", "Success");
//			} else {
//				model.addAttribute("message", "No student found for id: " + student.getId());
//				model.addAttribute("title", "Error");
//			}
//			return "result";
//		}
//	}
//
//	@RequestMapping(value = "/insert-all", method = RequestMethod.GET)
//	public String insertAll(Model model) {
//
////		List<Serializable> list = new ArrayList<Serializable>();
////		for (int i = 0; i < 10; i++) {
////			Student s = null;
////			try {
////				s = new Student("student-" + i, 21);
////				s.setId(String.valueOf(i));
////			} catch (GuidGeneratorException e) {
////				e.printStackTrace();
////			}
////			list.add(s);
////		}
////		try {
////			Student s = new Student();
////			s.setId(String.valueOf(1));
////			list.add(s);
////		} catch (GuidGeneratorException e) {
////			e.printStackTrace();
////		}
////		studentDao.insertAll(list);
////
////		List<Student> students = (List<Student>) studentDao.getAll();
////		model.addAttribute("serverTime", getformattedDate());
////		model.addAttribute("students", students);
////		model.addAttribute("title", "All Students");
////		logger.info("studentAll handler");
//		return "all";
//	}
}
