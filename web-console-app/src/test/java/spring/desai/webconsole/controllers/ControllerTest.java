package spring.desai.webconsole.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import spring.desai.common.model.dto.StudentDTO;

@WebAppConfiguration
@ActiveProfiles({"jdbc"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcTestConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, WithSecurityContextTestExecutionListener.class })
public class ControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mvc;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	
	@Before
	public void setup() {
		mvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	@Test
	@WithMockUser(username="rest",password="password",roles="REST_USER")
	public void testIt() throws Exception {
		System.out.println("ControllerTests.testIt()");
		// Default mapping request. 
		mvc.perform(get("")).andExpect(status().isOk()); //.andDo(print());
		
		// Path not found request
		mvc.perform(get("/student/studentid1")).andExpect(status().isNotFound()); //.andDo(print());
		
		// Valid request
		mvc.perform(get("/rest/student/student")).andExpect(status().isNotFound()); //.andDo(print());
		
		mvc.perform(get("/rest/student/all")).andExpect(status().isOk());
		
		// POST request testing
		StudentDTO dto = new StudentDTO(UUID.randomUUID().toString(), "MOCK_F", "MOCK_L", 24 , "ADDRESS", null, null , null);
		
		MvcResult results = mvc.perform(post("/rest/student/" + dto.getId() + "/create").with(csrf())
			.content(toJson(dto)).contentType(contentType))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$..firstname").value("MOCK_F"))
			.andExpect(jsonPath("$..lastname").value("MOCK_L"))
			.andReturn();
		
		// Duplicate save to test controller advice and exception handler
		mvc.perform(post("/rest/student/" + dto.getId() + "/create").with(csrf())
				.content(toJson(dto)).contentType(contentType))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("responseCode", is("BAD_REQUEST")))
				.andExpect(jsonPath("userMessage", containsString("Unique index or primary key violation")));
		
		dto = (StudentDTO) toObject(results.getResponse().getContentAsString(), StudentDTO.class);
		dto.setFirstname("Updated");
		dto.setLastname("UPDATED");
		
		mvc.perform(put("/rest/student/" + dto.getId() + "/update")
				.with(csrf())
				.content(toJson(dto)).contentType(contentType)).andExpect(status().isOk())
				.andExpect(jsonPath("$..firstname").value("Updated"))
				.andExpect(jsonPath("$..lastname").value("UPDATED"));
		
		mvc.perform(delete("/rest/student/" + dto.getId() + "/delete")
				.with(csrf())
				.content(toJson(dto)).contentType(contentType))
				.andExpect(status().isOk());

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
