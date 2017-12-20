package spring.desai.webconsole.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.UUID;



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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import spring.desai.common.model.dto.StudentDTO;

@WebAppConfiguration
@ActiveProfiles({"jdbc", "secure"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcTestConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class ControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mvc;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	
	@Before
	public void setup() {
		mvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testIt() throws Exception {
		System.out.println("ControllerTests.testIt()");
		// Default mapping request. 
		mvc.perform(get("")).andExpect(status().isOk()).andDo(print());
		
		// Path not found request
		mvc.perform(get("/student/studentid1")).andExpect(status().isNotFound()).andDo(print());
		
		// Valid request
		mvc.perform(get("/rest/student/student")).andExpect(status().isNotFound()).andDo(print());
		
		// POST request testing
		StudentDTO dto = new StudentDTO(UUID.randomUUID().toString(), "MOCK_F", "MOCK_L", 24 , "ADDRESS", null, null , null);
		
		MvcResult results = mvc.perform(post("/rest/student/save").principal(new Principal() {
			
			@Override
			public String getName() {
				return "rest";
			}
		}).content(toJson(dto)).contentType(contentType))
			.andExpect(status().isOk()).andDo(print())
			.andExpect(jsonPath("$.f_name").value("MOCK_F"))
			.andExpect(jsonPath("$.l_name").value("MOCK_L"))
			.andReturn();
		
		// Duplicate save to test controller advice and exception handler
		mvc.perform(post("/rest/student/save").content(toJson(dto)).contentType(contentType)).andExpect(status().isInternalServerError()).andDo(print());
		
		dto = (StudentDTO) toObject(results.getResponse().getContentAsString(), StudentDTO.class);
		dto.setFirstname("Updated");
		dto.setLastname("UPDATED");
		
		mvc.perform(post("/rest/student/update").content(toJson(dto)).contentType(contentType)).andExpect(status().isOk()).andDo(print())
			.andExpect(jsonPath("$.f_name").value("Updated"))
			.andExpect(jsonPath("$.l_name").value("UPDATED"));
		
		mvc.perform(delete("/rest/delete")).andExpect(status().isOk()).andDo(print()).andReturn().getResponse();

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
