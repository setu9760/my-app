package spring.desai.webconsole.controllers.rest;

import static java.lang.String.format;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class UserMaintananceController {

	private static final Logger logger = Logger.getLogger(UserMaintananceController.class);
	
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
	public ModelAndView resetSignOnStatus(@PathVariable String userId, Principal principal) throws Exception {
		ModelAndView mv = getAdminHomeModelAndView();
		String currentUser = principal.getName();
		if (userId.equalsIgnoreCase(currentUser)) {
			String msg = format("Admin User '%s' cannot reset its own signon status Unless the user has role SUPER assigned", currentUser);
			logger.warn(msg);
			mv.addObject("error", msg);
			return mv;
		}
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
		Object a = request.getParameter("adminUser");
		try {
			List<Role> roles = new ArrayList<>();
			roles.add(readOnlyService.getRestUserRole());
			if (a != null) {
				roles.add(readOnlyService.getAdminRole());
			}
			adminUserMaintananceService.createUser(dtoFactory.fromPersonDTOToUser(person), person.getId(), roles);
			mv.addObject("msg", "success");
		} catch (UsernameNotUniqueException e) {
			logger.warn(e.getMessage(), e);
			mv.addObject("error", e.getMessage());
		}
		return addDefaultObjects(mv);
	}
	
	@PostMapping(value = "/delete-user/{userId}")
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
	
//	@GetMapping(value="/update-user-details/{userId}")
//	public ModelAndView updateUserDetails(@PathVariable String userId){
//		ModelAndView mv = new ModelAndView("update-user-details");
//		if (adminUserMaintananceService.isExistingUser(userId))
//			mv.addObject("person", adminUserMaintananceService.getUser(userId));
//		else 
//			mv.addObject("error", format("Tried to update non-existing user '%s'", userId));
//		return mv;
//	}
//	
//	@PostMapping(value="/update-user-details")
//	public ModelAndView updateUserDetails(@ModelAttribute @Valid PersonDTO user) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		
//		adminUserMaintananceService.updateUserPersonalDetails(dtoFactory.fromPersonDTOToUser(user));
//		return mv;
//	}
	
	private ModelAndView getAdminHomeModelAndView(){
		return new ModelAndView("user_maintenance");
	}
	
	private ModelAndView addDefaultObjects(ModelAndView mv) {
		mv.addObject("person", new PersonDTO());
		mv.addObject("users", dtoFactory.fromUsersToPersonDTOs(adminUserMaintananceService.getAllUsers()));
		return mv;
	}
}
