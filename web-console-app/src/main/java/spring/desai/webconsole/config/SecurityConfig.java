package spring.desai.webconsole.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CsrfFilter;

import spring.desai.common.secure.handlers.CsrfTokenResponseHeaderBindingFilter;

@Order(100)
@Configuration
@Profile("secure")
@EnableWebSecurity
@ComponentScan({"spring.desai.common.secure"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
		
	@Autowired
	@Qualifier("loginSuccessHandler")
	private AuthenticationSuccessHandler loginSuccessHandler;
	
	@Autowired
	@Qualifier("logoutSuccessHandler")
	private LogoutHandler logoutSuccessHandler;
	
	@Autowired
	@Qualifier("authenticationFailureHandler")
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	@Qualifier("csrfTokenFilter")
	private CsrfTokenResponseHeaderBindingFilter csrfTokenFilter;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)/*.passwordEncoder(new BCryptPasswordEncoder())*/;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	     http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);
	     
	     http.authorizeRequests().antMatchers("/rest/**").access("hasRole('ROLE_REST_USER')")
			.and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
			.successHandler(loginSuccessHandler)
//			.failureUrl("/login?error=1").permitAll()
			.failureHandler(authenticationFailureHandler).permitAll()
			.and().logout().invalidateHttpSession(true).addLogoutHandler(logoutSuccessHandler).permitAll()
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().csrf();
	}
}
