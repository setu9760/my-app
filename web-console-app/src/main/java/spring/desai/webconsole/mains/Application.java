package spring.desai.webconsole.mains;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.desai.common.model.Student;
import spring.desai.webconsole.config.Config;

public class Application {

	public static final Logger log = Logger.getLogger("mainAppLogger");

	ThreadLocal<Student> tlStudent = new ThreadLocal<Student>();
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		
		context.registerShutdownHook();
		context.close();
	}
}
