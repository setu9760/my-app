package spring.desai.webconsole.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import spring.desai.common.model.Student;
import spring.desai.common.model.dto.StudentDTO;

@WebAppConfiguration
@ActiveProfiles("jdbc")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcTestConfig.class)
public class ControllerTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
//	@Autowired
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	
	@Before
	public void setup() {
		mvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testIt() throws Exception {
		for (String s : webApplicationContext.getBeanDefinitionNames()) {
			System.out.println(s);
		}
		
		mvc.perform(get("/rest/student/student")).andExpect(status().isNotFound()).andDo(print());
		
		mvc.perform(get("")).andExpect(status().isOk()).andDo(print());
		
		mvc.perform(get("/rest/student/studentid1")).andExpect(status().isOk()).andDo(print());
		
		mvc.perform(get("/student/studentid1")).andExpect(status().isNotFound()).andDo(print()).andReturn();
		
		mvc.perform(post("/rest/student/save")
					.content(json(new Student("MOCK_ID", "MOCK_F", "MOCK_L", 24, "ADDRESS")))
					.contentType(contentType))
			.andExpect(status().isOk())
			.andDo(print());
		
//		mvc.perform(get("/rest/student/MOCK_ID")).andExpect(status().isOk()).andDo(print());
	}
	
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
