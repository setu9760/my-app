package spring.desai.webconsole.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@EnableWebMvc  
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

//	@Bean
//	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//		RequestMappingHandlerAdapter handlerAdapter = super.requestMappingHandlerAdapter();
//		return handlerAdapter;
//	}
//	
//	@Bean
//	public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter(){
//		return new SimpleControllerHandlerAdapter();
//	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
	}
}
