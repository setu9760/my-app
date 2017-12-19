package spring.desai.webconsole.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "spring.desai.webconsole.interceptors", "spring.desai.common.interceptors" })
public class AspectsConfig {

}
