package spring.desai.common.repository.tests.jdbc;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import spring.desai.common.model.Tutor;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.TutorRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TutorRepositoryImplTest extends AbstractRepositoryTest<Tutor> {

	@Autowired
	private TutorRepository tutorRepository;


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		doSaveTest(new Tutor("TEST-ID-1", "f_name", "l_name", "subjectid2", "Some address", true));
	}

	@Test
	public void testSaveAll() {
		List<Tutor> l = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			l.add(new Tutor("TEST-" + i, "f_name_" + i, "l_name_" + i, "subjectid4", "Some address", true));
		}
		doSaveAllTest(l);
	}

	@Test
	public void testUpdate() {
		Tutor t = tutorRepository.findById("tutorid1");
		t.setF_name("UPDATED_F_NAME");
		t.setL_name("UPDATED_L_NAME");
		doUpdateTest(t, "tutorid1");
	}

	@Test
	public void testUpdateAll() {
		Collection<Tutor> origCollection = tutorRepository.findByName("f_name");
		
		Collection<Tutor> updateCollection = tutorRepository.findByName("f_name");
		for (Tutor t : updateCollection) {
			t.setF_name("UPDATED_F_NAME");
			t.setL_name("UPDATED_L_NAME");
		}
		doUpdateAllTest(origCollection, updateCollection, "tutorid");
	}

	@Test
	public void testGetTutorsForSubject() {
		Collection<Tutor> c = null; 
		
		c = tutorRepository.getTutorsForSubject(null);
		assertThat(c, is(not(nullValue())));
		assertThat(c, is(empty()));
		
		c = tutorRepository.getTutorsForSubject("NON_EXISTANT_ID");
		assertThat(c, is(not(nullValue())));
		assertThat(c, is(empty()));
		
		c = tutorRepository.getTutorsForSubject("subjectid2");
		assertThat(c.size(), is(equalTo(2)));
	}

	@Test
//	(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		doGetAllTest(7);
	}

	@Test
	public void testFindById() {
		doFindByIdTest("tutorid1");
	}

	@Test
	public void testFindByName() {
		doFindByNameTest("f_name4", 3, 7);
	}

	@Test
	public void testDeleteById() {
		tutorRepository.save(new Tutor("ID_TO_DELETE", "A", "B", "subjectid3", "A", false));
		doDeleteByIdTest("ID_TO_DELETE");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteByName() {
		doDeleteByNameTest("f_name6");
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
	public Class<Tutor> getPersistableClazz() {
		return Tutor.class;
	}

	@Override
	public BasePersistableRepository<Tutor> getRepo() {
		return tutorRepository;
	}

}
