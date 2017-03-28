package spring.desai.webconsole.service.impl;

import static spring.desai.common.utils.UserRoleConstants.USER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import spring.desai.common.model.Role;
import spring.desai.common.model.User;
import spring.desai.common.model.User.SIGN_ON_STATUS;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.UserRepository;
import spring.desai.common.repository.UsrrRepository;
import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.exception.ServiceException;
import spring.desai.common.utils.I18N;

@Transactional
@Service("adminUserMaintananceService")
public class AdminUserMaintananceServiceImpl implements AdminUserMaintananceService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UsrrRepository usrrRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void createUser(User user, String rawPassword) throws ServiceException {
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findById(USER));
		createUser(user, rawPassword, roles);
	}

	public Collection<User> getAllUsers() throws ServiceException {
		return userRepository.getAll();
	}

	@Override
	public void updateUserPersonalDetails(User user) throws ServiceException {
		userRepository.update(user);
	}

	@Override
	public void createUser(User user, String rawPassword, Collection<Role> rolesToAssign) throws ServiceException {
		notNull(user);
		notNull(rawPassword);
		notNull(rolesToAssign);
		if (!isExistingUser(user.getId())) {
			userRepository.save(user);
			usrrRepository.createUser(user.getId(), passwordEncoder.encode(rawPassword));
			assignRoles(user, rolesToAssign);
		} else {
			throw new ServiceException("User " + user.getId() + " already exists.");
		}
	}

	@Override
	public void removeUser(User user) throws ServiceException {
		notNull(user);
		roleRepository.revokeAllRoles(user.getId());
		usrrRepository.deleteUser(user.getId());
		userRepository.deleteById(user.getId());
	}

	@Override
	public boolean isExistingUser(String userId) throws ServiceException {
		notNull(userId);
		return userRepository.isExistingUser(userId);
	}

	@Override
	public void assignRoles(User user, Collection<Role> roles) throws ServiceException {
		notNull(user);
		notNull(roles);
		// TODO should be able to pass collection to repository to persist
		// instead of looping over in service layer.
		for (Role role : roles) {
			roleRepository.assignRoleToUser(user.getId(), role);
		}
	}

	@Override
	public void unassignRoles(User user, Collection<Role> roles) throws ServiceException {
		notNull(user);
		notNull(roles);
		// roles.forEach(r -> roleRepository.unassignRole(user.getId(), r));
		for (Role role : roles) {
			roleRepository.unassignRole(user.getId(), role);
		}
	}

	@Override
	public void revokeAllRoles(User user) throws ServiceException {
		notNull(user);
		roleRepository.revokeAllRoles(user.getId());
	}

	@Override
	public void resetUserSignOn(String userId) throws ServiceException {
		notNull(userId);
		usrrRepository.unlockUserAccount(userId);
		usrrRepository.updateSignOnStatus(userId, SIGN_ON_STATUS.LOGGED_OUT);
		usrrRepository.resetFailedAttempt(userId);
	}

	private void notNull(Object obj) {
		Assert.notNull(obj, I18N.getString("error.null.object"));
	}
}
