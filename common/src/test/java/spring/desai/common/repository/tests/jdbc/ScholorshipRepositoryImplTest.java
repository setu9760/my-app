package spring.desai.common.repository.tests.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import spring.desai.common.model.Scholarship;
import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.ScholarshipRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScholorshipRepositoryImplTest extends AbstractRepositoryTest<Scholarship> {

	@Autowired
	ScholarshipRepository scholorshipRepository;
	
	@Override
	protected boolean doRefereshDbBetweenTests() {
		return true;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		doSaveTest(new Scholarship("TEST-ID1", "CCHD232", ScholorshipType.NATIONAL_PART, 1400, 300, false, false, null, "studentid1"));
	}

	@Test
	public void testSaveAll() {
		List<Scholarship> l = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			l.add(new Scholarship("TEST-ID-" + i, "CCHD232", ScholorshipType.NATIONAL_PART, 1400, 300, false, false, null, "studentid" + i));
		}
		doSaveAllTest(l);
	}

	@Test
	public void testUpdate() {
		Scholarship s = scholorshipRepository.findById("schlrid1");
		s.setAdditionalComments("UPDATED ADDITIONAL COMMENTS");
		s.setExternalRef("UPDATED_REF1");
		s.setPaidAmount(400d);
		doUpdateTest(s, "schlrid1");
	}

	@Test
	public void testUpdateAll() {
		Collection<Scholarship> origColl = scholorshipRepository.findByName("MGMT_PART");
		
		Collection<Scholarship> ipdateColl = scholorshipRepository.findByName("MGMT_PART");
		for (Scholarship s : ipdateColl) {
			s.setAdditionalComments("UPDATED ADDITIONAL COMMENTS");
			s.setExternalRef("UPDATED_REF1");
			s.setTotalAmount(400d);
		}
		doUpdateAllTest(origColl, ipdateColl, "schlrid");
	}

	@Test
//	(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		doGetAllTest(5);
	}

	@Test
	public void testFindById() {
		doFindByIdTest("schlrid1");
	}

	@Test
	public void testFindByName() {
		doFindByNameTest("MGMT_PART", 4, 5);
	}
	
	@Test
	public void testFindByType() {
		Collection<Scholarship> c = scholorshipRepository.findByType(null);
		assertNotNull(c);
		
		c = scholorshipRepository.findByType(ScholorshipType.STATE_PART);
		assertSize(c, 1);
	}

	@Test
	public void testFindByStudentId() {
		Collection<Scholarship> c = scholorshipRepository.findByStudentId(null);
		assertNotNull(c);
		
		c = scholorshipRepository.findByStudentId("studentid1");
		assertSize(c, 2);
	}


	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteById() {
		scholorshipRepository.save(new Scholarship("ID-TO-DELETE", "CCHD232", ScholorshipType.NATIONAL_PART, 1400, 300, false, false, null, "studentid1"));
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
		doCountAllTest(5);
	}

	@Override
	public Class<Scholarship> getPersistableClazz() {
		return Scholarship.class;
	}

	@Override
	public BasePersistableRepository<Scholarship> getRepo() {
		return scholorshipRepository;
	}

}
