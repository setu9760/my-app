package spring.desai.webconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter handlerAdapter = super.requestMappingHandlerAdapter();
		return handlerAdapter;
	}
	
	@Bean
	public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter(){
		return new SimpleControllerHandlerAdapter();
	}
}
