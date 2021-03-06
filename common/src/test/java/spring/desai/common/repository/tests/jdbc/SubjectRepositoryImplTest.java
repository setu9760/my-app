package spring.desai.common.repository.tests.jdbc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import spring.desai.common.model.Subject;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.SubjectRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SubjectRepositoryImplTest extends AbstractRepositoryTest<Subject> {

	@Autowired
	SubjectRepository subjectRepository;
	
	@Override
	protected boolean doRefereshDbBetweenTests() {
		return true;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		doSaveTest(new Subject("TEST-ID", "ABCD", "BASIC", true));
	}

	@Test
	public void testSaveAll() {
		List<Subject> l = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			l.add(new Subject("ID-"+i, "NAME-"+i, "BASIC", true));
		}
		doSaveAllTest(l);
	}

	@Test
	public void testUpdate() {
		Subject s = subjectRepository.findById("subjectid1");
		s.setName("NAMNE-XYZ");
//		s.setCostCode("RESEARCH");
		doUpdateTest(s, "subjectid1");
	}

	@Test
	public void testUpdateAll() {
		Collection<Subject> origSubs = subjectRepository.findByName("name");
		
		Collection<Subject> updateSubs = subjectRepository.findByName("name");
		for (Subject s : updateSubs) {
			s.setName("UPDATED_NAME");
		}
		doUpdateAllTest(origSubs, updateSubs, "subjectid");
	}

	@Test
	public void testFindById() {
		doFindByIdTest("subjectid1");
	}

	@Test
	public void testFindByIds() {
	}

	@Test
	public void testFindByName() {
		doFindByNameTest("name1", 1, 5);
	}

	@Test
	public void testFindByCostCode() {
		Collection<Subject> c = null;
		c = subjectRepository.findByCostCode(null);
		assertThat(c, is(not(nullValue())));
		
		c = subjectRepository.findByCostCode("BASIC");
		assertThat(c, hasSize(2));
	}

	@Test
	public void testFindByMandatory() {
		Collection<Subject> c = subjectRepository.findByMandatory(true);
		assertThat(c.size(), is(equalTo(3)));
		
		c = subjectRepository.findByMandatory(false);
		assertThat(c.size(), is(equalTo(2)));
	}

	@Test
	public void testGetSubjectsForStudentId() {
		Collection<Subject> c = null;
		c = subjectRepository.getSubjectsForStudentId(null);
		assertThat(c, is(not(nullValue())));
		assertThat(c.size(), is(equalTo(0)));
		
		c = subjectRepository.getSubjectsForStudentId("");
		assertThat(c.size(), is(equalTo(0)));
		
		c = subjectRepository.getSubjectsForStudentId("studentid1");
		assertThat(c.size(), is(equalTo(4)));
	}

	@Test
//	(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		doGetAllTest(5);
	}

	@Test
	public void testDeleteById() {
		subjectRepository.save(new Subject("ID_TO_DELETE", "D", "BASIC", true));
		doDeleteByIdTest("ID_TO_DELETE");
	}
	
	@Test
	public void testRemoveStudentFromSubject() {
		Collection<Subject> subs = subjectRepository.getSubjectsForStudentId("studentid1");
		for (Subject subject : subs) {
			subjectRepository.removeStudentFromSubject("studentid1", subject);
		}
		assertNotEquals(subs.size(), subjectRepository.getSubjectsForStudentId("studentid1").size());
		for (Subject subject : subs) {
			assertFalse(subjectRepository.isStudentInSubject("studentid1", subject));
		}
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
	public Class<Subject> getPersistableClazz() {
		return Subject.class;
	}

	@Override
	public BasePersistableRepository<Subject> getRepo() {
		return subjectRepository;
	}

}
