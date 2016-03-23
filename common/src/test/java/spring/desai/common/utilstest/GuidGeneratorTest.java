package spring.desai.common.utilstest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import spring.desai.common.utils.GuidGenerator;

public class GuidGeneratorTest {

//	GuidGenerator guidGenerator;
//	String expectedGuid;
//	ApplicationContext context ;
//	@Before
//	public void before() {
//		 context = new ClassPathXmlApplicationContext("testbeanRef.xml");
//
//		dataSource = context.getBean(DataSource.class);
//	}
//
//	DataSource dataSource;

	@Test
	public void testA() throws SQLException {
//		Connection connection = dataSource.getConnection();
//		PlatformTransactionManager ptm = context.getBean(PlatformTransactionManager.class);
//		
//		System.out.println();
	}

	// @Before
	// public void before() throws GuidGeneratorException {
	// guidGenerator = GuidGenerator.getInstance();
	// expectedGuid = guidGenerator.getGuid();
	// }
	//
	// @Test
	// public void testGetInstance() {
	// assertNotNull(guidGenerator);
	// assertEquals(guidGenerator, GuidGenerator.getInstance());
	// System.out.println(expectedGuid);
	// }
	//
	// @Test
	// public void testGetGuid() throws GuidGeneratorException {
	// assertNotNull(expectedGuid);
	// assertFalse(expectedGuid.equals(guidGenerator.getGuid()));
	// System.out.println(expectedGuid);
	// assertFalse(guidGenerator.getGuid().equals(guidGenerator.getGuid()));
	// System.out.println(expectedGuid);
	// }
	//
	// @Test
	// public void testGetGuid_name() throws GuidGeneratorException {
	// expectedGuid = guidGenerator.getGuid("Setu Desai");
	// assertFalse(expectedGuid.substring(0, 2).equals("SB"));
	// assertFalse(!expectedGuid.substring(0, 2).equals("SD"));
	// System.out.println(expectedGuid);
	// }

}
