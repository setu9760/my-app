package spring.desai.webconsole.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

@Configuration
@Profile("jpa")
@ComponentScan("spring.desai.common.repository.impl.jpa")
public class JpaConfig {

	public PersistenceExceptionTranslationPostProcessor geTranslationPostProcessor(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
}
