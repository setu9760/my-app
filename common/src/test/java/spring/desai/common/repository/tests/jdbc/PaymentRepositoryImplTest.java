package spring.desai.common.repository.tests.jdbc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
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

import spring.desai.common.model.Payment;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.repository.BasePersistableRepository;
import spring.desai.common.repository.PaymentRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaymentRepositoryImplTest extends AbstractRepositoryTest<Payment> {

	@Autowired
	PaymentRepository paymentRepository;
	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		doSaveTest(new Payment("TEST-ID1", 300, PaymentType.CASH, "studentid1"));
	}

	@Test
	public void testSaveAll() {
		List<Payment> l = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			l.add(new Payment("TEST_ID_" + i, 300 , PaymentType.CASH, "studentid" + i));
		}
		doSaveAllTest(l);
	}

	@Test
	public void testUpdate() {
		Payment p = paymentRepository.findById("payment3");
		p.setAmount(100d);
		p.setComments("SOME_RANDOM_COMMENT");
		p.setPaymentType(PaymentType.CASH);
		p.setStud_id("studentid2");
		doUpdateTest(p, "payment3");
	}

	@Test
	public void testUpdateAll() {
		Collection<Payment> origPayments = paymentRepository.findByName("payment");
		
		Collection<Payment> updatePayments = paymentRepository.findByName("payment");
		int i = 1;
		for (Payment p : updatePayments) {
			p.setComments("UPDATED_COMMENTS");
			p.setAmount(200 * i++);
		}
		
		doUpdateAllTest(origPayments, updatePayments, "payment");
	}

	@Test
	//(expected=UnsupportedOperationException.class)
	public void testGetAll() {
		doGetAllTest(5);
	}

	@Test
	public void testFindById() {
		doFindByIdTest("payment1");
	}

	@Test
	public void testFindByName() {
		doFindByNameTest("payment1", 1, 5);
	}
	
	@Test
	public void testFindbyStudentId() {
		Collection<Payment> c = paymentRepository.findbyStudentId(null);
		assertNotNull(c);
		
		c = paymentRepository.findbyStudentId("SOME_ID");
		assertNotNull(c);
		
		c = paymentRepository.findbyStudentId("studentid1");
		assertThat(c.size(), is(equalTo(2)));
	}

	@Test
	public void testFindByType() {
		Collection<Payment> c = paymentRepository.findByType(null);
		assertNotNull(c);
		
		c = paymentRepository.findByType(PaymentType.PAY6);
		assertNotNull(c);
		
		c = paymentRepository.findByType(PaymentType.CASH);
		assertThat(c.size(), is(equalTo(2)));
	}

	@Test
	public void testDeleteById() {
		paymentRepository.save(new Payment("ID_TO_DELETE", 300d, PaymentType.DD, "studentid4"));
		doDeleteByIdTest("ID_TO_DELETE");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testDeleteByName() {
		doDeleteByNameTest("payment3");
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
	public Class<Payment> getPersistableClazz() {
		return Payment.class;
	}

	@Override
	public BasePersistableRepository<Payment> getRepo() {
		return paymentRepository;
	}

}
