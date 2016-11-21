package spring.desai.webconsole.controllers.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import spring.desai.common.model.Role;
import spring.desai.common.model.User;
import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.ReadOnlyService;

@RestController
@Profile("secure")
@RequestMapping(value = "/admin", produces = "application/json;charset=UTF-8")
public class UserMaintananceController {

	private static final Logger logger = Logger.getLogger(UserMaintananceController.class);
	
	@Autowired
	@Qualifier("adminUserMaintananceService")
	private AdminUserMaintananceService adminUserMaintananceService;
	
	@Autowired
	@Qualifier("readOnlyService")
	private ReadOnlyService readOnlyService; 
	
	@RequestMapping(value = "/resetSignOnStatus/{userId}", method = RequestMethod.GET)
	public void resetSignOnStatus(@PathVariable String userId, Principal principal) throws Exception {
		String currentUser = principal.getName();
//		Collection<? extends GrantedAuthority> roles = ud.getAuthorities();
		// TODO Check if this user has super role. If so allow the user to reset itself otherwise log it as warning. 
		if (userId.equalsIgnoreCase(currentUser)) {
			logger.warn("Admin User " + currentUser + " cannot reset its own signon status Unless the user has role SUPER assigned.");
			return;
		}
		adminUserMaintananceService.resetUserSignOn(userId);
	}

	@RequestMapping(value = "/register-user", method = RequestMethod.GET)
	public ModelAndView registerUser() throws Exception {
		return new ModelAndView("register");
	}
	
	@RequestMapping(value = "/register-user", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("register");
		
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
			mv.addObject("msg", "success");
		} catch (Exception e) {
			mv.addObject("error", e.getMessage());
		}
		
		
		return mv;
	}
}
