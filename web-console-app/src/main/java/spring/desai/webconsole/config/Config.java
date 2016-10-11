package spring.desai.webconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@Import({ AspectsConfig.class, JdbcConfig.class, JpaConfig.class, ServiceConfig.class, RmiConfig.class, SecurityConfig.class, DefaultSecurityConfig.class, WebConfig.class })
@ComponentScan({ "spring.desai.webconsole.controllers", "spring.desai.common.rowmappers", "spring.desai.webconsole.config.core"
		/*,  "spring.desai.webconsole.*", "spring.desai.webconsole.config.*", "spring.desai.common.*",
		"spring.desai.webconsole.core.*", "spring.desai.webconsole.config.aspects.*",
		"spring.desai.webconsole.controllers.*", "spring.desai.webconsole.JdbcDaoImpl.*" */ })
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Config {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
