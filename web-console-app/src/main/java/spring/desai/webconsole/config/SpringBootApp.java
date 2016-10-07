package spring.desai.webconsole.config;

import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
//import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * Commented this out as it failed to deploy on stanalone tomcat instance. 
 */

//@Configuration
//@Profile("embedded")
//@Import(Config.class) 
//@EnableAutoConfiguration
//public class SpringBootApp extends SpringBootServletInitializer{
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringBootApp.class, args);
//	}
//	
//	@Bean
//	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
//		return new TomcatEmbeddedServletContainerFactory() {
//
//			@Override
//			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
//				tomcat.enableNaming();
//				return super.getTomcatEmbeddedServletContainer(tomcat);
//			}
//
//			@Override
//			protected void postProcessContext(Context context) {
//				ContextResource resource = new ContextResource();
//				resource.setName("jdbc/test");
//				resource.setType(DataSource.class.getName());
//				resource.setProperty("driverClassName", "org.h2.Driver");
//				resource.setAuth("Container");
//				resource.setProperty("factory", "org.apache.commons.dbcp2.BasicDataSourceFactory");
//				resource.setProperty("url", "jdbc:h2:file:C:/data/test;DB_CLOSE_ON_EXIT=FALSE");
//				resource.setProperty("maxTotal", "100");
//				resource.setProperty("maxIdle", "30");
//				resource.setProperty("maxWaitMillis", "10000");
//				resource.setProperty("username", "sa");
//				resource.setProperty("password", "");
//				context.getNamingResources().addResource(resource);
//			}
//
//		};
//	}
//}
