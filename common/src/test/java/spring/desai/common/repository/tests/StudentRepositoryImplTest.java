package spring.desai.common.repository.tests;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import spring.desai.common.model.pojo.Student;
import spring.desai.common.repository.StudentRepository;

//@RunWith(SpringJUnit4ClassRunner.class)
//@org.springframework.test.context.ContextConfiguration(classes = Configs.class, loader = AnnotationConfigContextLoader.class)
public class StudentRepositoryImplTest {
//
//	@Autowired(required = true)
//	@Qualifier("studentRepository")
//	private StudentRepository studentRepository;

	@Test
	public void testSave() {
//		try {
//			studentRepository.save(new Student());
//			studentRepository.save(new Student());
//
//			// fail("Failed");
//		} catch (Exception e) {
//			System.out.println("error");
//			e.printStackTrace();
//		}

	}

	@Test
	public void testFindById() {
//		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
//		fail("Not yet implemented");
	}

	@Test
	public void testFindbyName() {
//		fail("Not yet implemented");
	}

}
