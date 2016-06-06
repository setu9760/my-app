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
import org.springframework.dao.DuplicateKeyException;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.I18N;

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
		doNullSaveTest(subjectRepository);
		Subject orig = new Subject("TEST_NAME", CostCode.BASIC, true);
		try {
			subjectRepository.save(orig);
			fail("Should have failed with IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), startsWith(I18N.getNoArgString("error.null.id")));
		}
		try {
			orig = new Subject("", null, CostCode.BASIC, true);
			subjectRepository.save(orig);
			fail("Should have thrown DataAccessException while saving null to non-null column");
		} catch (Exception e) {
			assertThat(e, is(instanceOf(RepositoryDataAccessException.class)));
		}
		orig = new Subject("ABC-123", "ABCD", CostCode.BASIC, true);
		subjectRepository.save(orig);
		Subject retrieved = subjectRepository.findById("ABC-123");
		assertThat(retrieved, is(not(nullValue())));
		assertThat(retrieved, is(equalTo(orig)));
		
		try {
			subjectRepository.save(orig);
			fail("Should have thrown Exception while trying to save duplicate data.");
		} catch (RepositoryDataAccessException e) {
			assertThat(e.contains(DuplicateKeyException.class), is(true));
		}
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
