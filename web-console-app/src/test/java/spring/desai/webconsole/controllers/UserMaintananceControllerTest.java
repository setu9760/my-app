package spring.desai.webconsole.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
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
@ActiveProfiles({ "jdbc" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcTestConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, WithSecurityContextTestExecutionListener.class })
@SuppressWarnings("unused")
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
	@WithMockUser(username="admin", password="password", roles="ADMIN_USER")
	public void testIt() throws Exception{
		System.out.println("UserMaintananceControllerTest.testIt()");
		String id= UUID.randomUUID().toString();
		mvc.perform(post("/admin/register-user")
				.with(csrf())
				.param("firstname", "ABC")
				.param("id", id).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("lastname", "ABC").param("address", "ABC")
				.param("password1", "password").param("adminUser", "yes"))
				.andExpect(status().isOk())
				.andExpect(view().name("user_maintenance"))
				.andExpect(model().attributeExists("msg"))
				.andExpect(model().attribute("msg", is(String.format("user '%s' created successfully", id))));
		
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
