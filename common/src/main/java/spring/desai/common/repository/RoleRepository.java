package spring.desai.common.repository;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import spring.desai.common.model.Role;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 * Specific interface of {@link BasePersistableRepository} for type {@link Role}
 * @author desai
 */
public interface RoleRepository extends BasePersistableRepository<Role> {

	/**
	 * @param role
	 * @return Returns the collection of usersIds which has this particular role assigned
	 * @throws RepositoryDataAccessException
	 */
	Collection<String> getUserIdsforRole(Role role) throws RepositoryDataAccessException;
	
	/**
	 * @param userId
	 * @return Returns the roles assigned to the userId
	 * @throws RepositoryDataAccessException
	 */
	Collection<? extends GrantedAuthority> getRolesForUserId(String userId) throws RepositoryDataAccessException;
	
	/**
	 * Check if the role is available to the userId 
	 * @param userId
	 * @param role
	 * @return true if role is available to the user, false otherwise
	 * @throws RepositoryDataAccessException
	 */
	boolean isRoleAvailableToUser(String userId, Role role) throws RepositoryDataAccessException;
	
	/**
	 * Assigns the role to this userId
	 * @param userId
	 * @param role
	 * @throws RepositoryDataAccessException
	 */
	void assignRoleToUser(String userId, Role role) throws RepositoryDataAccessException;
	
	/**
	 * Revokes the role from userId
	 * @param userId
	 * @param role
	 * @throws RepositoryDataAccessException
	 */
	void unassignRoles(String userId, Collection<Role> role) throws RepositoryDataAccessException;
	
	/**
	 * Revokes all roles for this userId
	 * @param userId
	 * @throws RepositoryDataAccessException
	 */
	void revokeAllRoles(String userId) throws RepositoryDataAccessException;
}
