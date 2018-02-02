package spring.desai.webconsole.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
public class HomeController extends BaseController {

	public HomeController() {
		essentialLoggers.add("org.springframework");
		essentialLoggers.add("org.springframework.aop");
		essentialLoggers.add("org.springframework.beans");
		essentialLoggers.add("org.springframework.boot");
		essentialLoggers.add("org.springframework.core");
		essentialLoggers.add("org.springframework.context");
		essentialLoggers.add("org.springframework.jdbc.core");
		essentialLoggers.add("org.springframework.security");
		essentialLoggers.add("org.springframework.web");
		essentialLoggers.add("org.apache.commons.dbcp2");
		essentialLoggers.add("org.hibernate");
		essentialLoggers.add("jdbcdaoLog");
		essentialLoggers.add("org.hibernate");
		Enumeration<?> loggers = LogManager.getCurrentLoggers();
		
		Logger rootLogger = LogManager.getRootLogger();
		loggersMap.put(rootLogger.getName(), rootLogger);
		while (loggers.hasMoreElements()) {
			Logger logger = (Logger) loggers.nextElement();
			for (String s : essentialLoggers) 
				if (logger.getName().startsWith(s))
					loggersMap.put(logger.getName(), logger);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		model.addAttribute("serverTime", getformattedDate());
		model.addAttribute("title", "Home");
		logger.info("returning home");
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		logger.info("returning login form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/";
		}
		return "login";
	}

	@RequestMapping(value = "log4jAdmin", method = { RequestMethod.GET, RequestMethod.POST })
	public String log4jAdmin(Model model, HttpServletRequest request, HttpServletResponse response) {
		String logger = request.getParameter("loggerName");
		String level = request.getParameter("changeLevelTo");
		if (null != logger && null != level)
			changeLevel(logger, level);
		model.addAttribute("loggerList", getListOfLoggers());
		return "log4jAdmin";
	}

	private void changeLevel(String logger, String level) {
		Logger l = LogManager.getLogger(logger);
		if (l != null)
			System.out.println(String.format("changing logging for %s from %s to %s", logger, String.valueOf(l.getEffectiveLevel()), level));
		else 
			System.err.println(String.format("supplied logger name %s didn't match with any loggers", logger));
	}

	List<String> essentialLoggers = new ArrayList<>();
	HashMap<String, Logger> loggersMap = new HashMap<>();
	
	private List<LoggerDTO> getListOfLoggers() {
		List<LoggerDTO> list = new ArrayList<>();
		for (String s : loggersMap.keySet()) {
			Logger logger = (Logger) loggersMap.get(s);
			if (logger != null) {
				LoggerDTO l = new LoggerDTO(logger.getName(),
						null != logger.getParent() ? logger.getParent().getName() : null,
						String.valueOf(logger.getEffectiveLevel()));
				list.add(l);

			}
		}
		Collections.sort(list);
		return list;
	}

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
}
