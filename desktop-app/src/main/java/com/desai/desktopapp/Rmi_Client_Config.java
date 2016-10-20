package com.desai.desktopapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import spring.desai.common.service.AdminUserMaintananceService;

@Configuration
public class Rmi_Client_Config {
	
	@Bean(name = "rmiAdminUserMaintananceService")
	public RmiProxyFactoryBean getRmiAdminUserMaintananceService() {
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		rmiProxy.setServiceUrl("rmi://BRIWS0609:9595/adminUserMaintananceService");
		rmiProxy.setServiceInterface(AdminUserMaintananceService.class);
		return rmiProxy;
	}
}
