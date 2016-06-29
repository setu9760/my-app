package spring.desai.common.service;

import static spring.desai.common.utils.UserRoleConstants.USER;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import spring.desai.common.model.Role;
import spring.desai.common.model.User;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.UserRepository;
import spring.desai.common.repository.UsrrRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.I18N;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UsrrRepository usrrRepository;

	@Override
	@Transactional
	public void createUser(User user, String encryptedPassword) throws ServiceException {
		try {
			Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findById(USER));
			createUser(user, encryptedPassword, roles);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("createUser(user, password)", e);
		}
	}

	@Override
	@Transactional
	public void createUser(User user, String encryptedPassword, Collection<Role> rolesToAssign) throws ServiceException {
		notNull(user);
		notNull(encryptedPassword);
		notNull(rolesToAssign);
		try {
			if (!isExistingUser(user.getId())) {
				userRepository.save(user);
				usrrRepository.createUser(user.getId(), encryptedPassword);
				assignRoles(user, rolesToAssign);
			} else {
				// TODO throw user already exists exception
			}
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("createUser(user, password, roles)", e);
		}
	}
	
	@Override
	@Transactional
	public void removeUser(User user) throws ServiceException {
		notNull(user);
		try {
			roleRepository.revokeAllRoles(user.getId());
			usrrRepository.deleteUser(user.getId());
			userRepository.deleteById(user.getId());
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("removeUser(user)", e);
		}
	}
	

	@Override
	public boolean isExistingUser(String userId) throws ServiceException {
		notNull(userId);
		try {
			return userRepository.isExistingUser(userId);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("isExistingUser(userId)", e);
		}
	}

	@Override
	@Transactional
	public void assignRoles(User user, Collection<Role> roles) throws ServiceException {
		notNull(user);
		notNull(roles);
		try {
			// TODO should be able to pass collection to repository to persist instead of looping over in service layer.
			for (Role role : roles) {
				roleRepository.assignRoleToUser(user.getId(), role);
			}
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("assignRoles(user, roles)", e);
		}
	}
	
	@Override
	public void unassignRoles(User user, Collection<Role> roles) throws ServiceException {
		notNull(user);
		notNull(roles);
		try {
			roleRepository.unassignRoles(user.getId(), roles);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("unassignRoles(user, roles)", e);	
		}
	}
	
	@Override
	public void revokeAllRoles(User user) throws ServiceException {
		notNull(user);
		try {
			roleRepository.revokeAllRoles(user.getId());
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("revokeAllRoles(user)", e);	
		}
	}

	private void notNull(Object obj) {
		Assert.notNull(obj, I18N.getString("error.null.object"));
	}
}
