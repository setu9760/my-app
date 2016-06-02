package spring.desai.common.repository.tests;

import static org.junit.Assert.fail;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.repository.SubjectRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SubjectRepositoryImplTest extends AbstractRepositoryTest {

	@Autowired
	SubjectRepository subjectRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
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
	public void testFindById() {
		doPersistableFindByIdTest(subjectRepository, "subjectid1");
	}

	@Test
	public void testFindByIds() {
	}

	@Test
	public void testFindByName() {
		doPersistableFindByNameTest(subjectRepository, "name1", 1, 5);
	}

	@Test
	public void testFindByCostCode() {
		Collection<Subject> c = null;
		c = subjectRepository.findByCostCode(null);
		assertThat(c, is(not(nullValue())));
		
		c = subjectRepository.findByCostCode(CostCode.BASIC);
		assertThat(c, hasSize(2));
	}

	@Test
	public void testFindByMandatory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubjectsForStudentId() {
		fail("Not yet implemented");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		doPersistableGetAllTest(subjectRepository, 5);
	}

	@Test
	public void testDeleteById() {
		subjectRepository.save(new Subject("ID_TO_DELETE", "D", CostCode.BASIC, true));
		doPersistableDeleteByIdTest(subjectRepository, "ID_TO_DELETE");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteByName() {
		doPersistableDeleteByNameTest(subjectRepository, "SOME_NAME");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteAll() {
		doPersistableDeleteAllTest(subjectRepository);
	}

	@Test
	public void testCountAll() {
		doPersistableCountAllTest(subjectRepository, 5);;
	}

}
