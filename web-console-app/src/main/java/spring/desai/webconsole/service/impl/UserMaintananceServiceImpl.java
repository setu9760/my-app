package spring.desai.webconsole.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import spring.desai.common.model.User;
import spring.desai.common.repository.UserRepository;
import spring.desai.common.repository.UsrrRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.UserMaintananceService;
import spring.desai.common.service.exception.ServiceException;
import spring.desai.common.utils.I18N;

@Transactional
@Service("userMaintananceService")
public class UserMaintananceServiceImpl implements UserMaintananceService {

	@Autowired
	private UsrrRepository usrrRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void updateUserDetails(User user) throws ServiceException {
		notNull(user);
		try {
			userRepository.update(user);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("updateUserDetails(user)", e);
		}
	}

	@Override
	public void updatePassword(User user, String newRawPassword) throws ServiceException {
		notNull(user);
		notNull(newRawPassword);
		try {
			String currentPassword = usrrRepository.getUserLoginDetails(user.getId()).getPassword();
			if (passwordEncoder.matches(newRawPassword, currentPassword)) {
				
				// TODO throw same new password exception
			} 
			usrrRepository.updatePassword(user.getId(), passwordEncoder.encode(newRawPassword));
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("updatepassword(user, newPassword, oldPassword)", e);
		}
	}
	
	private void notNull(Object obj) {
		Assert.notNull(obj, I18N.getString("error.null.object"));
	}

}
