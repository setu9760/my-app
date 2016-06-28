package spring.desai.common.service;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.desai.common.model.Payment;
import spring.desai.common.model.Role;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.model.User;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.UserRepository;
import spring.desai.common.repository.UsrrRepository;

//@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UsrrRepository usrrRepository;

	@Override
	@Transactional(value=TxType.MANDATORY)
	public void createUser(User user, String encryptedPassword) {
		if (!isExistingUser(user.getId())) {
			userRepository.save(user);
			usrrRepository.createUser(user.getId(), encryptedPassword);
		}
	}

	@Override
	public boolean isExistingUser(String userId) {
		return userRepository.isExistingUser(userId);
	}

	@Override
	public void assignRole(User user, Role role) {

	}

	@Override
	public void assignRoles(User user, Collection<Role> roles) {
		// TODO Auto-generated method stub

	}

}
