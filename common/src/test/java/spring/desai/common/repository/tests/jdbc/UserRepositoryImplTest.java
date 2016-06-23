package spring.desai.common.repository.tests.jdbc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import spring.desai.common.model.User;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.UserRepository;
import spring.desai.common.utils.I18N;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryImplTest extends AbstractRepositoryTest<User> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected boolean doRefereshDbBetweenTests() {
		return true;
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		doSaveTest(new User("USER1234", "f_name", "l_name", "address"));
	}

	@Test
	public void testSaveAll() {
		List<User> l = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			l.add(new User("U-ID-" + i, "fname", "lname", "address"));
		}
		doSaveAllTest(l);
	}

	@Test
	public void testUpdate() {
		User updated = userRepository.findById("USER-1");
		updated.setAddress("UPDATED ADDRESS");
		updated.setAccountLocked(true);
		doUpdateTest(updated, "USER-1");
	}

	@Test
	public void testUpdateAll() {
		Collection<User> orig = userRepository.findByName("F_NAME");
		
		Collection<User> updated = userRepository.findByName("F_NAME");
		for (User u : updated) {
			u.setAddress("UPDATED ADDRESS");
			u.setAccountLocked(true);
		}
		doUpdateAllTest(orig, updated, "USER-");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		doGetAllTest(5);
	}

	@Test
	public void testFindById() {
		doFindByIdTest("USER-1");
	}

	@Test
	public void testFindByName() {
		doFindByNameTest("F_NAME", 5, 5);
	}
	
	@Test
	public void testFindLockedOutUsers() {
		Collection<User> c = userRepository.findLockedOutUsers();
		assertSize(c, 3);
	}

	@Test
	public void testFindCurrentlyActiveUsers() {
		Collection<User> c = userRepository.findCurrentlyActiveUsers();
		assertSize(c, 1);
	}

	@Test
	public void testFindUsersWithMaxFailedAttempts() {
		Collection<User> c = userRepository.findUsersWithMaxFailedAttempts();
		assertSize(c, 2);
	}

	@Test
	public void testResetLockedOutUser() {
		try {
			userRepository.resetLockedOutUser(null);
			fail("Should have failed with IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
		
		userRepository.resetLockedOutUser(new User("USER-4", "sdf", "d", "s"));
		User u = userRepository.findById("USER-4");
		assertThat(u.isAccountLocked(), is(false));
	}

	@Test
	public void testResetLockedOutUsers() {
		try {
			userRepository.resetLockedOutUsers(null);
			fail("Should have failed with IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is(equalTo(I18N.getNoArgString("error.null.object"))));
		}
		
		Collection<User> c = userRepository.findLockedOutUsers();
		userRepository.resetLockedOutUsers(c);
		
		c = userRepository.findLockedOutUsers();
		assertSize(c, 0);
	}

	@Test
	public void testDeleteById() {
		userRepository.save(new User("ID_TO_DELETE", "f_name", "l_name", "address"));
		doDeleteByIdTest("ID_TO_DELETE");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteByName() {
		doDeleteByNameTest("SOME_NAME");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteAll() {
		doDeleteAllTest();
	}

	@Test
	public void testCountAll() {
		doCountAllTest(5);
	}

	@Override
	public Class<User> getPersistableClazz() {
		return User.class;
	}

	@Override
	public BasePersistableRepository<User> getRepo() {
		return userRepository;
	}

}
