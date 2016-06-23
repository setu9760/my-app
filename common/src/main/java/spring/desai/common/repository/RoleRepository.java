package spring.desai.common.repository;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import spring.desai.common.model.Role;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface RoleRepository extends BasePersistableRepository<Role> {

	Collection<String> getUserIdsforRole(Role role) throws RepositoryDataAccessException;
	
	Collection<? extends GrantedAuthority> getRolesForUserId(String userId) throws RepositoryDataAccessException;
	
	boolean isRoleAvailableToUser(String userId, Role role) throws RepositoryDataAccessException;
	
}
