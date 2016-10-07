package spring.desai.webconsole.mains;

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
		System.out.println(encoder.encode("password5"));
		
		System.out.println(encoder.matches("password5", "$2a$10$gIhzQ2LrI3JMaPZnPwFI/uynuqKQ/4sNLYSyLtmQVSDg.h./WwtHq"));
		
	}
}
