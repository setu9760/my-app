package spring.desai.common.repository.tests;

import static org.hamcrest.Matchers.equalTo;
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

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Cost;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.CostCodeRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostCodeRepositoryImplTest extends AbstractRepositoryTest<Cost> {

	@Autowired
	private CostCodeRepository costCodeRepository;

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		doSaveTest(new Cost(CostCode.UCC9, 1200d));
	}

	@Test
	public void testSaveAll() {
		List<Cost> l = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			l.add(new Cost(CostCode.valueOf("TEST_" + i), 500 + i));
		}
		doSaveAllTest(l);
	}

	@Test
	public void testUpdate() {
		Cost c = costCodeRepository.findById("BASIC");
		c.setAmount(100d);
		doUpdateTest(c, "BASIC");
	}

	@Test
	public void testUpdateAll() {
		Collection<Cost> origCosts = costCodeRepository.findByName("TEST_");
		
		Collection<Cost> updateCosts = costCodeRepository.findByName("TEST_");
		int i = 1;
		for (Cost c : updateCosts) {
			c.setAmount(100d * i++);
		}
		doUpdateAllTest(origCosts, updateCosts, "TEST_");
	}

	@Test
	public void testFindByCode() {
		
		Cost c = costCodeRepository.findByCode(null);
		assertThat(c, is(nullValue()));
		
		c = costCodeRepository.findByCode(CostCode.BASIC);
		assertThat(c, is(not(nullValue())));
		assertThat(c.getAmount(), is(equalTo(1000d)));
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		doGetAllTest(5);
	}

	@Test
	public void testFindById() {
		doFindByIdTest("BASIC");
	}

	@Test
	public void testFindByName() {
		doFindByNameTest("UCC1", 1, 9);
	}

	@Test
	public void testDeleteById() {
		costCodeRepository.save(new Cost(CostCode.UCC10, 300d));
		doDeleteByIdTest("UCC10");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteByName() {
		doDeleteByNameTest("UCC10");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteAll() {
		doDeleteAllTest();
	}

	@Test
	public void testCountAll() {
		doCountAllTest(9);
	}

	@Override
	public Class<Cost> getPersistableClazz() {
		return Cost.class;
	}

	@Override
	public BasePersistableRepository<Cost> getRepo() {
		return costCodeRepository;
	}
	
	@Override
	protected boolean doNullFieldTest() {
		super.doNullFieldTest();
		return false;
	}

}
