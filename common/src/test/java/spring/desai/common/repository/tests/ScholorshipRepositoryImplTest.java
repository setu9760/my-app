package spring.desai.common.repository.tests;

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
		List<Scholorship> l = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			l.add(new Scholorship("TEST-ID-" + i, "CCHD232", ScholorshipType.NATIONAL_PART, 1400, 300, false, false, null, "studentid" + i));
		}
		doSaveAllTest(l);
	}

	@Test
	public void testUpdate() {
		Scholorship s = scholorshipRepository.findById("schlrid1");
		s.setAdditional_comments("UPDATED ADDITIONAL COMMENTS");
		s.setExternal_ref("UPDATED_REF1");
		s.setPaid_amount(400d);
		doUpdateTest(s, "schlrid1");
	}

	@Test
	public void testUpdateAll() {
		Collection<Scholorship> origColl = scholorshipRepository.findByName("MGMT_PART");
		
		Collection<Scholorship> ipdateColl = scholorshipRepository.findByName("MGMT_PART");
		for (Scholorship s : ipdateColl) {
			s.setAdditional_comments("UPDATED ADDITIONAL COMMENTS");
			s.setExternal_ref("UPDATED_REF1");
			s.setTotal_amount(400d);
		}
		doUpdateAllTest(origColl, ipdateColl, "schlrid");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		doGetAllTest(4);
	}

	@Test
	public void testFindById() {
		doFindByIdTest("schlrid1");
	}

	@Test
	public void testFindByName() {
		doFindByNameTest("MGMT_PART", 3, 4);
	}
	
	@Test
	public void testFindByType() {
		Collection<Scholorship> c = scholorshipRepository.findByType(null);
		assertNotNull(c);
		
		c = scholorshipRepository.findByType(ScholorshipType.STATE_PART);
		assertSize(c, 1);
	}

	@Test
	public void testFindByStudentId() {
		Collection<Scholorship> c = scholorshipRepository.findByStudentId(null);
		assertNotNull(c);
		
		c = scholorshipRepository.findByStudentId("studentid1");
		assertSize(c, 2);
	}


	@Test
	public void testDeleteById() {
		scholorshipRepository.save(new Scholorship("ID-TO-DELETE", "CCHD232", ScholorshipType.NATIONAL_PART, 1400, 300, false, false, null, "studentid1"));
		doDeleteByIdTest("ID-TO-DELETE");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteByName() {
		doDeleteByNameTest("MGMT_PART");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteAll() {
		doDeleteAllTest();
	}

	@Test
	public void testCountAll() {
		doCountAllTest(4);
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
