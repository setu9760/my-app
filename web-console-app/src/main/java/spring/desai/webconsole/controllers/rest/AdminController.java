package spring.desai.webconsole.controllers.rest;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import spring.desai.common.model.Role;
import spring.desai.common.model.dto.DTOFactory;
import spring.desai.common.model.dto.PersonDTO;
import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.exception.UsernameNotUniqueException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	@Qualifier("adminUserMaintananceService")
	private AdminUserMaintananceService adminUserMaintananceService;
	
	@Autowired
	@Qualifier("readOnlyService")
	private ReadOnlyService readOnlyService; 
	
	private DTOFactory dtoFactory = DTOFactory.getInstance();
	
	@GetMapping
	public ModelAndView admin(){
		return addDefaultObjects(getAdminHomeModelAndView());
	}

	@PostMapping(value = "/reset-signin-status/{userId}")
	@PreAuthorize("#userId != principal.username")
	public ModelAndView resetSignOnStatus(@PathVariable String userId) throws Exception {
		ModelAndView mv = getAdminHomeModelAndView();
		adminUserMaintananceService.resetUserSignOn(userId);
		mv.addObject("msg", format("reset status for user '%s'", userId));
		return addDefaultObjects(mv);
	}

	@PostMapping(value = "/register-user")
	public ModelAndView registerUser(HttpServletRequest request, @ModelAttribute @Valid PersonDTO person, BindingResult result) throws Exception {
		ModelAndView mv = getAdminHomeModelAndView();
		if (result.hasErrors()) {
			mv.addObject("error", result.getAllErrors());
			return addDefaultObjects(mv);
		}
		try {
			adminUserMaintananceService.createUser(dtoFactory.fromPersonDTOToUser(person), person.getId(), extractRoles(request));
			mv.addObject("msg", format("user '%s' created successfully", person.getId()));
		} catch (UsernameNotUniqueException e) {
			logger.warn(e.getMessage(), e);
			mv.addObject("error", e.getMessage());
		}
		return addDefaultObjects(mv);
	}
	
	private List<Role> extractRoles(HttpServletRequest request) {
		List<Role> roles = new ArrayList<>();
		roles.add(readOnlyService.getRestUserRole());
		if (request.getParameter("adminUser") != null) 
			roles.add(readOnlyService.getAdminRole());
		if (request.getParameter("hrUser") != null)
			roles.add(readOnlyService.getRole("ROLE_HR_USER"));
		if (request.getParameter("financeUser") != null)
			roles.add(readOnlyService.getRole("ROLE_FINANCE_USER"));
		if (request.getParameter("mgmtUser") != null)
			roles.add(readOnlyService.getRole("ROLE_MGMT_USER"));
		return roles;
	}

	@PostMapping(value = "/delete-user/{userId}")
	@PreAuthorize("#userId != principal.username")
	public ModelAndView deleteUser(@PathVariable String userId) {
		ModelAndView mv = new ModelAndView("user_maintenance");
		mv.addObject("person", new PersonDTO());
		if (adminUserMaintananceService.isExistingUser(userId)) {
			adminUserMaintananceService.removeUser(userId);
			mv.addObject("msg", format("user '%s' deleted successfully", userId));
		} else {
			mv.addObject("error", format("Tried to delete non-existing user '%s'", userId));
		}
		return addDefaultObjects(mv);
	}
	
	private ModelAndView getAdminHomeModelAndView(){
		return new ModelAndView("user_maintenance");
	}
	
	private ModelAndView addDefaultObjects(ModelAndView mv) {
		mv.addObject("person", new PersonDTO());
		mv.addObject("users", dtoFactory.fromUsersToPersonDTOs(adminUserMaintananceService.getAllUsers()));
		return mv;
	}
}
