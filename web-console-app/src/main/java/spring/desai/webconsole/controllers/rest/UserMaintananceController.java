package spring.desai.webconsole.controllers.rest;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.desai.common.service.AdminUserMaintananceService;

@RestController
@Profile("secure")
@RequestMapping(value = "/admin", produces = "application/json;charset=UTF-8")
public class UserMaintananceController {

	private static final Logger logger = Logger.getLogger(UserMaintananceController.class);
	
	@Autowired
	@Qualifier("adminUserMaintananceService")
	private AdminUserMaintananceService adminUserMaintananceService;
	
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
}
