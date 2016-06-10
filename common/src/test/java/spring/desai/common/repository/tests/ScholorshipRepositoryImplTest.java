package spring.desai.common.repository.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.model.pojo.Scholorship;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.ScholorshipRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScholorshipRepositoryImplTest extends AbstractRepositoryTest<Scholorship> {

	@Autowired
	ScholorshipRepository scholorshipRepository;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		doSaveTest(new Scholorship("TEST-ID1", "CCHD232", ScholorshipType.NATIONAL_PART, 1400, 300, false, false, null, "studentid1"));
	}

	@Test
	public void testSaveAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindByType() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByStudentId() {
		fail("Not yet implemented");
	}


	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountAll() {
		fail("Not yet implemented");
	}

	@Override
	public Class<Scholorship> getPersistableClazz() {
		return Scholorship.class;
	}

	@Override
	public BasePersistableRepository<Scholorship> getRepo() {
		return scholorshipRepository;
	}

}
