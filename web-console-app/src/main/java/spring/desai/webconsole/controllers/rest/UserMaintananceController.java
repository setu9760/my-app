package spring.desai.webconsole.controllers.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.desai.common.model.Role;
import spring.desai.common.model.User;
import spring.desai.common.model.dto.DTOFactory;
import spring.desai.common.model.dto.PersonDTO;
import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.ReadOnlyService;

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
		ModelAndView mv = new ModelAndView("user_maintenace");
		mv.addObject("users", dtoFactory.fromUsersToPersonDTOs(adminUserMaintananceService.getAllUsers()));
		return mv;
	}

	@PostMapping(value = "/resetSignOnStatus")
	public ModelAndView resetSignOnStatus2(HttpServletRequest request, Principal principal) throws Exception {
		ModelAndView mv = new ModelAndView("user_maintenace");
		mv.addObject("users", dtoFactory.fromUsersToPersonDTOs(adminUserMaintananceService.getAllUsers()));
		String userId = request.getParameter("userId");
		String currentUser = principal.getName();
		if (userId.equalsIgnoreCase(currentUser)) {
			logger.warn("Admin User " + currentUser + " cannot reset its own signon status Unless the user has role SUPER assigned.");
			mv.addObject("error", "Admin User " + currentUser + " cannot reset its own signon status Unless the user has role SUPER assigned.");
			return mv;
		}
		adminUserMaintananceService.resetUserSignOn(userId);mv.addObject("msg", "reset status for user " + userId);
		return mv;
	}

	@PostMapping(value = "/register-user")
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("user_maintenance");
		model.addObject("users", dtoFactory.fromUsersToPersonDTOs(adminUserMaintananceService.getAllUsers()));
		String f = request.getParameter("firstName");
		String l = request.getParameter("lastName");
		String u = request.getParameter("username");
		String p = request.getParameter("password1");
		Object a = request.getParameter("adminUser");
		try {
			List<Role> roles = new ArrayList<>();
			roles.add(readOnlyService.getRestUserRole());
			if (a != null) {
				roles.add(readOnlyService.getAdminRole());
			}
			adminUserMaintananceService.createUser(new User(u, f, l, ""), p, roles);
			model.addObject("msg", "success");
		} catch (Exception e) {
			logger.warn("Tried to create user with existing username: " + u, e);
			model.addObject("error", e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/list-user", method = RequestMethod.GET)
	public ModelAndView listUsers() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("users",dtoFactory.fromUsersToPersonDTOs(adminUserMaintananceService.getAllUsers()));
		return mv;
	}
	
	@RequestMapping(value = "/updateUserDetails", method = RequestMethod.POST)
	public ModelAndView updateUserDetails(@RequestBody PersonDTO user) throws Exception {
		ModelAndView mv = new ModelAndView();
		adminUserMaintananceService.updateUserPersonalDetails(dtoFactory.fromPersonDTOToUser(user));
		return mv;
	}
}
