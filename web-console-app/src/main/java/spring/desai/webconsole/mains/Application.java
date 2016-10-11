package spring.desai.webconsole.mains;

import static org.mockito.Matchers.startsWith;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import spring.desai.common.model.Student;
import spring.desai.webconsole.config.Config;

public class Application {

	public static final Logger log = Logger.getLogger("mainAppLogger");

	ThreadLocal<Student> tlStudent = new ThreadLocal<Student>();
	
	public static void main(String[] args) {
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//
//		
//		context.registerShutdownHook();
//		context.close();

		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("password"));
		
		System.out.println(encoder.matches("password", "$2a$10$4fkJEFXuj8hf3qMunAw0YerLMWFy42NaosUNS3lpMYar1fX9OHbMW"));
	
	}
}
