package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.pojo.Role;
import spring.desai.common.model.pojo.User;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface RoleRepository extends BasePersistableRepository<Role> {

	Collection<String> getUserIdsforRole(Role role) throws RepositoryDataAccessException;
	
	Collection<Role> getRolesForUserId(String userId) throws RepositoryDataAccessException;
	
	boolean isRoleAvailableToUser(User userId, Role role) throws RepositoryDataAccessException;
	
}
