package spring.desai.webconsole.test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemoryBasedAbstractTest {

	static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:*testBeanRef.xml");
	}

	@Before
	public void beforeMethod() {
		context.refresh();
	}

	@Test
	public void testIt() {

	}

	@Test
	public void testThat() {

	}

	@AfterClass
	public static void tearDown() throws Exception {
		context.close();
	}
}