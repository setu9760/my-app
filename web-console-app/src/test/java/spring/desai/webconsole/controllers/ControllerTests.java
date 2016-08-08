package spring.desai.webconsole.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@ActiveProfiles("jdbc")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcTestConfig.class)
public class ControllerTests {

	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mvc;
	
	@Before
	public void setup() {
		mvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testIt() throws Exception {
		mvc.perform(get("/rest/student/student")).andExpect(status().isBadRequest()).andDo(print());
		
		mvc.perform(get("")).andExpect(status().isOk()).andDo(print());
		
		mvc.perform(post("")).andExpect(status().isMethodNotAllowed()).andDo(print());
		
		mvc.perform(get("/rest/student/studentid1")).andExpect(status().isOk()).andDo(print());
		
		mvc.perform(get("/student/studentid1")).andExpect(status().isNotFound()).andDo(print()).andReturn();
	}

}
