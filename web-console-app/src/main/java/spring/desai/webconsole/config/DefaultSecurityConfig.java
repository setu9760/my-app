package spring.desai.webconsole.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import spring.desai.webconsole.config.SecurityConfig.AjaxAuthenticationSuccessHandler;

@Order(100)
@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter{

//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		
//		
//		auth.inMemoryAuthentication().withUser("test123").password("password").roles("USER").and().withUser("admin123")
//				.password("password").roles("USER", "ADMIN");
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
		
//		antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//		.and().authorizeRequests().antMatchers("/**").access("hasRole('ROLE_USER')")
//		.and().formLogin().permitAll()/**.failureUrl("/login?error").usernameParameter("username").passwordParameter("password")*/
//		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
//		.and().exceptionHandling().accessDeniedPage("/403.jsp")
//		.and().exceptionHandling().accessDeniedPage("/403")
//		.and().csrf()
//		.and().httpBasic();
	}
	
}
