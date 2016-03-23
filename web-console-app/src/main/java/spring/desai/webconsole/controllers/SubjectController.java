package spring.desai.webconsole.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.desai.common.model.pojo.Subject;
import spring.desai.common.utils.GuidGeneratorException;

@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(subjectValidator);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String SubjectAll(Model model) {
		List<Subject> subjects = (List<Subject>) subjectDao.getAll();
		model.addAttribute("serverTime", getformattedDate());
		model.addAttribute("subjects", subjects);
		model.addAttribute("title", "All Subjects");
		logger.info("subjectAll handler");
		return "all";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String subjectInsertForm(Model model) throws GuidGeneratorException {
		logger.info("subjectInsert get request handler");
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
		model.addAttribute("serverTime", getformattedDate());
		return "insert";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String subjectInsertResult(@ModelAttribute("subject") Subject subject, Model model, BindingResult bindingResult) {
		logger.info("subjectInsert post request handler");
		subjectValidator.validate(subject, bindingResult);
		model.addAttribute("serverTime", getformattedDate());
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "there was an error completing the request");
			model.addAttribute("title", "Error");
			return "insert";
		} else {
			subjectDao.insert(subject);
			model.addAttribute("message", "Successfully save subject: " + subject);
			model.addAttribute("title", "Success");
			return "result";
		}

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String subjectSearchForm(Model model) throws GuidGeneratorException {
		logger.info("subjectSearch get request handler");
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
		model.addAttribute("serverTime", getformattedDate());
		model.addAttribute("title", "Search Subject");
		return "subject-search";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String subjectSearchResult(@ModelAttribute("subject") Subject subject, Model model, BindingResult bindingResult) {
		logger.info("subjectSearch post request handler");
		model.addAttribute("serverTime", getformattedDate());
		return "result";
	}
}
