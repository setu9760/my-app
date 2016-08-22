package spring.desai.webconsole.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("aspectsEnabled")
@ComponentScan("spring.desai.webconsole.config.aspects")
public class AspectsConfig {

}
