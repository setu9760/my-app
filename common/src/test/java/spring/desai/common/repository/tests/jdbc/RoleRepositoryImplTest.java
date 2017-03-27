package spring.desai.common.repository.tests.jdbc;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import spring.desai.common.model.Role;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.I18N;

public class RoleRepositoryImplTest extends AbstractRepositoryTest<Role> {

	@Autowired
	private RoleRepository roleRepository; 
	
	
	@Override
	protected boolean doRefereshDbBetweenTests() {
		return true;
	}
	
	@Override
	protected boolean doNullFieldTest() {
		return super.doNullFieldTest();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		doSaveTest((new Role("admin1", "administrator")));
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testSaveAll() {
		List<Role> l = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			l.add(new Role("user1", "normal user"));
		}
		roleRepository.saveAll(l);
	}

	@Test
	public void testUpdate() {
		Role r = roleRepository.findById("READ_ONLY");
		r.setDescription("Updated readonly description");
		doUpdateTest(r, "READ_ONLY");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testUpdateAll() {
		Collection<Role> updateRoles = roleRepository.findByName("ROLE");
		for (Role r : updateRoles) {
			r.setDescription("Updated description");
		}
		roleRepository.updateAll(updateRoles);
	}
	
	@Test
	public void testGetUserIdsforRole() {
		Collection<String> c = null;
				
		try {
			c = roleRepository.getUserIdsforRole(null);
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}		
		
		try {
			c = roleRepository.getUserIdsforRole(new Role("",""));
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}	
		
		c = roleRepository.getUserIdsforRole(new Role("ABCD",""));
		assertSize(c, 0);
		
		c = roleRepository.getUserIdsforRole(new Role("ROLE1",""));
		assertSize(c, 2);
	}

	@Test
	public void testGetRolesForUserId() {
		Collection<? extends GrantedAuthority> c = roleRepository.getRolesForUserId(null);
		assertSize(c, 0);
		
		c = roleRepository.getRolesForUserId("NON_EXISTANT_USER");
		assertSize(c, 0);
		
		c = roleRepository.getRolesForUserId("USER-1");
		assertSize(c, 2);
	}

	@Test
	public void testIsRoleAvailableToUser() {
		boolean isAvailable = false; 
		try {
			isAvailable = roleRepository.isRoleAvailableToUser(null, null);
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}
		
		try {
			isAvailable = roleRepository.isRoleAvailableToUser(null, new Role("", ""));
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}	
		
		isAvailable = roleRepository.isRoleAvailableToUser(null, new Role("SOME_ROLE", ""));
		assertFalse(isAvailable);
		
		isAvailable = roleRepository.isRoleAvailableToUser("SOME_USER", new Role("SOME_ROLE", ""));
		assertFalse(isAvailable);
		
		isAvailable = roleRepository.isRoleAvailableToUser("USER-1", new Role("ROLE3", ""));
		assertFalse(isAvailable);
		
		isAvailable = roleRepository.isRoleAvailableToUser("USER-1", new Role("ROLE2", ""));
		assertTrue(isAvailable);
	}
	
	@Test
	public void testAssignRoleToUser() {
		Role role = roleRepository.findById("ROLE3");
		try {
			roleRepository.assignRoleToUser(null, null);
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}
		
		try {
			roleRepository.assignRoleToUser("USER-1", null);
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}
		
		try {
			roleRepository.assignRoleToUser(null, role);
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e, instanceOf(RepositoryDataAccessException.class));
		}
		boolean isAvailable = roleRepository.isRoleAvailableToUser("USER-1", new Role("ROLE3", ""));
		assertFalse(isAvailable);
		
		roleRepository.assignRoleToUser("USER-1", role);
		isAvailable = roleRepository.isRoleAvailableToUser("USER-1", new Role("ROLE3", ""));
		assertTrue(isAvailable);
		
		roleRepository.isRoleAvailableToUser("USER-1", new Role("ROLE3", ""));
	}
	
	@Test
	public void testUnAssignRoleForUser() {
		Role role = roleRepository.findById("ROLE2");
		try {
			roleRepository.unassignRole(null, null);
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}
		try {
			roleRepository.unassignRole("USER-1", null);
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.object")));
		}
	
		boolean isAvailable = roleRepository.isRoleAvailableToUser("USER-1", role);
		assertTrue(isAvailable);
		
		roleRepository.unassignRole("USER-1", role);
		isAvailable = roleRepository.isRoleAvailableToUser("USER-1", role);
		assertFalse(isAvailable);
	}
	
	@Test
	public void testRevokeAllRoles() {
		
		try {
			roleRepository.revokeAllRoles(null);
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}
		
		try {
			roleRepository.revokeAllRoles("");
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}
		
		int orig = roleRepository.getRolesForUserId("USER-1").size();
		
		roleRepository.revokeAllRoles("USER-1");
		
		int update = roleRepository.getRolesForUserId("USER-1").size();
		
		assertNotEquals(orig, update);
		assertEquals(0, update);
	}

	@Test
	public void testGetAll() {
		doGetAllTest(7);
	}

	@Test
	public void testFindById() {
		doFindByIdTest("ADMIN");
	}

	@Test
	public void testFindByName() {
		doFindByNameTest("ROLE", 5, 7);
	}

	@Test
	public void testDeleteById() {
		roleRepository.save(new Role("ID_TO_DELETE", "role for deletebyId Test"));
		doDeleteByIdTest("ID_TO_DELETE");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteByName() {
		doDeleteByNameTest("ROLE1");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteAll() {
		doDeleteAllTest();
	}

	@Test
	public void testCountAll() {
		doCountAllTest(7);
	}
	
	@Override
	public Class<Role> getPersistableClazz() {
		return Role.class;
	}

	@Override
	public BasePersistableRepository<Role> getRepo() {
		return roleRepository;
	}

}
