package com.desai.desktopapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import spring.desai.common.dao.StudentDao;
import spring.desai.common.dao.SubjectDao;
import spring.desai.common.dao.TutorDao;

@Configuration
public class Rmi_Client_Config {

	@Value("rmi://BRIWS0609:9595/studentService")
	String STUDENT_SERVICE_URL;

	@Value("rmi://BRIWS0609:9595/tutorService")
	String TUTOR_SERVICE_URL;

	@Value("rmi://BRIWS0609:9595/subjectService")
	String SUBJECT_SERVICE_URL;

	@Bean(name = "studentService")
	public RmiProxyFactoryBean getRmiStudentProxyFactoryBean() {
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		rmiProxy.setServiceUrl(STUDENT_SERVICE_URL);
		rmiProxy.setServiceInterface(StudentDao.class);
		return rmiProxy;
	}

	@Bean(name = "tutorService")
	public RmiProxyFactoryBean getRmiTutorProxyFactoryBean() {
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		rmiProxy.setServiceUrl(TUTOR_SERVICE_URL);
		rmiProxy.setServiceInterface(TutorDao.class);
		return rmiProxy;
	}

	@Bean(name = "subjectService")
	public RmiProxyFactoryBean getRmiSubjectProxyFactoryBean() {
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		rmiProxy.setServiceUrl(SUBJECT_SERVICE_URL);
		rmiProxy.setServiceInterface(SubjectDao.class);
		return rmiProxy;
	}

}
