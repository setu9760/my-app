package spring.desai.common.repository.tests.jdbc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import spring.desai.common.model.Student;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.StudentRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentRepositoryImplTest extends AbstractRepositoryTest<Student> {

	@Autowired()
	StudentRepository studentRepository;

	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		doSaveTest(new Student("ABCDS-ID-0123", "ABCDE", "ABCDE", 20, "Address"));
	}

	@Test
	public void testSaveAll() throws Exception {
		List<Student> l = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			l.add(new Student("TEST-ALL-" + i, "f_name-" + i, "l_name-" + i, 25, "addr"));
		}
		doSaveAllTest(l);
		
//		l = new ArrayList<>();
//		for (int i = 0; i < 501; i++) {
//			l.add(new Student("TEST-BATCH-ALL-" + i, "f_name-" + i, "l_name-" + i, 25, "addr"));
//		}
//		studentRepository.saveAll(l);;
	}

	@Test
	public void testUpdate() {
		Student updatedPersistable = studentRepository.findById("studentid1");
		updatedPersistable.setF_name("UPDATED_F_NAME");
		updatedPersistable.setL_name("UPDATED_L_NAME");
		doUpdateTest(updatedPersistable, "studentid1");
	}
	
	@Test
	public void testUpdateAll() {
		
		Collection<Student> origStuds = studentRepository.findByName("f_name");
		
		Collection<Student> updateStuds = studentRepository.findByName("f_name");
		for (Student student : updateStuds) {
			student.setF_name("UPDATED_F_NAME");
			student.setL_name("UPDATED_L_NAME");
		}
		doUpdateAllTest(origStuds, updateStuds, "studentid");
	}
	
	@Test
	public void testFindById() {
		doFindByIdTest("studentid1");
	}
	
	@Test
	public void testFindbyName() {
		doFindByNameTest("f_name4", 2, 5);
	}

	@Test
//	(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		doGetAllTest(5);
	}

	@Test
	public void testGetStudentsForSubjectId() {
		Collection<Student> c = null;
		c = studentRepository.getStudentsForSubjectId(null);
		assertThat(c, is(not(nullValue())));
		assertThat(c, hasSize(0));
		
		c = studentRepository.getStudentsForSubjectId("");
		assertThat(c, is(not(nullValue())));
		assertThat(c, hasSize(0));
		
		c = studentRepository.getStudentsForSubjectId("NON_EXISTING_SUBJ_ID");
		assertThat(c, is(not(nullValue())));
		assertThat(c, hasSize(0));
		
		c = studentRepository.getStudentsForSubjectId("subjectid1");
		assertThat(c, is(not(nullValue())));
		assertThat(c, hasSize(4));
	}

	@Test
	public void testDeleteById() {
		studentRepository.save(new Student("ID_TO_DELETE", "NAME", "NAME", 25, ""));
		doDeleteByIdTest("ID_TO_DELETE");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteByName() {
		doDeleteByNameTest("NAME_TO_DELETE");
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
	public Class<Student> getPersistableClazz() {
		return Student.class;
	}
	
	@Override
	public BasePersistableRepository<Student> getRepo() {
		return studentRepository;
	}
	
}
