package spring.desai.webconsole.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ActiveProfiles({ "jdbc", "secure" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcTestConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class UserMaintananceControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

	@Before
	public void setup() {
		mvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testIt() throws Exception{
		System.out.println("UserMaintananceControllerTest.testIt()");
		mvc.perform(post("/admin/register-user").param("firstName", "ABC")
				.param("lastName", "ABC").param("username", "ABC")
				.param("password1", "password").param("adminUser", "yes"))
				.andExpect(model().attribute("msg","success")).andDo(print());
		
		mvc.perform(get("/admin/list-user")).andDo(print());
	}

	public String toJson(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	protected <T> Object toObject(String jsonString, Class<T> clazz) throws IOException {
		MockHttpInputMessage httpInputMessage = new MockHttpInputMessage(jsonString.getBytes());
		return mappingJackson2HttpMessageConverter.read(clazz, httpInputMessage);
	}
}
