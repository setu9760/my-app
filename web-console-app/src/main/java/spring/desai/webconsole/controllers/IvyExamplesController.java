package spring.desai.webconsole.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IvyExamplesController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, Principal principal, HttpServletRequest request) {
		model.addAttribute("serverTime", getformattedDate());
		model.addAttribute("title", "Home");
		logger.info("returning home");
		return "home";
	}
	
	@RequestMapping(value = "log4jAdmin", method = {RequestMethod.GET, RequestMethod.POST})
	public String log4jAdmin(Model model, HttpServletRequest request, HttpServletResponse response){
		model.addAllAttributes(request.getParameterMap());
		return "log4jAdmin";
	}

//	@RequestMapping(value = "/reloadLog4J", method = RequestMethod.GET)
//	public String reloadlog4J(Model model) {
//		logger.info("Reloading Log4J prop file");
//		try {
//			String path = "C:\\log4j.properties";
//			PropertyConfigurator.configure(path);
//			model.addAttribute("message", "Successfully reloaded log4j");
//			logger.info("reloaded successfully");
//		} catch (Exception e) {
//			model.addAttribute("message",
//					"failed to reload log4j properties see error log");
//			logger.error("log4j reloading failed", e);
//		}
//		return "result";
//	}
//
//	@RequestMapping(value = "/student", method = RequestMethod.GET)
//	public String studentHome(Model model) {
//
//		String formattedDate = LocalDateTime.now().toString(
//				"dd-MMMM-yyyy  kk-mm-ss  zzz");
//		model.addAttribute("serverTime", formattedDate);
//		model.addAttribute("title", "student");
//		logger.info("student handler");
//		return "main";
//	}
//
//	@RequestMapping(value = "/subject", method = RequestMethod.GET)
//	public String subjectHome(Model model) {
//
//		model.addAttribute("serverTime", getformattedDate());
//		model.addAttribute("title", "subject");
//		logger.info("subject handler");
//		return "main";
//	}
//
//	@RequestMapping(value = "/tutor", method = RequestMethod.GET)
//	public String tutorHome(Model model) {
//
//		model.addAttribute("serverTime", getformattedDate());
//		model.addAttribute("title", "tutor");
//		logger.info("tutor handler");
//		return "main";
//	}
//
//	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
//	public ModelAndView defaultPage() {
//
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Spring Security Login Form - Database Authentication");
//		model.addObject("message", "This is default page!");
//		model.setViewName("hello");
//		return model;
//
//	}
//
//	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
//	public ModelAndView adminPage() {
//
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Spring Security Login Form - Database Authentication");
//		model.addObject("message", "This page is for ROLE_ADMIN only!");
//		model.setViewName("admin");
//		return model;
//
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
//			@RequestParam(value = "logout", required = false) String logout) {
//
//		ModelAndView model = new ModelAndView();
//		if (error != null) {
//			model.addObject("error", "Invalid username and password!");
//		}
//
//		if (logout != null) {
//			model.addObject("msg", "You've been logged out successfully.");
//		}
//		model.setViewName("login");
//
//		return model;
//
//	}
//
	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;

	}
//
//	@Override
//	void initBinder(WebDataBinder binder) {
//		// NO-OP
//	}
}
