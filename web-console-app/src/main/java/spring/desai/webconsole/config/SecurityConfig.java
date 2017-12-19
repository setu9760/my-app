package spring.desai.webconsole.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CsrfFilter;

import spring.desai.common.secure.MaxLoginAttemptsExceededException;
import spring.desai.common.secure.UserAlreadyLoggedinException;
import spring.desai.common.secure.handlers.CsrfTokenResponseHeaderBindingFilter;
import spring.desai.common.secure.user.UserLoginDetails;

@Order(100)
@Configuration
@Profile("secure")
@EnableWebSecurity
@ComponentScan({"spring.desai.common.secure"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
		
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
	private BCryptPasswordEncoder passwordEncrypter; 
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getDaoAuthenticationProvider()).userDetailsService(userDetailsService).passwordEncoder(passwordEncrypter);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/img/**", "/resources/**", "/scripts/**");
		super.configure(web);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	     http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);
	     
	     http.authorizeRequests().antMatchers("/rest/**").permitAll()
	     	.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN_USER')")
			.and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
			.successHandler(loginSuccessHandler)
			.failureHandler(authenticationFailureHandler).permitAll()
			.and().logout().invalidateHttpSession(true).addLogoutHandler(logoutSuccessHandler).permitAll()
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().csrf()
			.and().sessionManagement().maximumSessions(1).expiredUrl("/login?logout");
	}
	
	public AuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPreAuthenticationChecks(new UserDetailsChecker() {

			@Override
			public void check(UserDetails user) {
				if (!user.isAccountNonLocked()) 
					throw new LockedException("User account is locked");
				if (!user.isEnabled()) 
					throw new DisabledException("User is disabled");
				if (!user.isAccountNonExpired()) 
					throw new AccountExpiredException("User account has expired");
				if (user instanceof UserLoginDetails) {
					UserLoginDetails uld = (UserLoginDetails) user;
					if (uld.getFailedAttempts() >= 3) {
						throw new MaxLoginAttemptsExceededException(uld.getUsername(), uld.getFailedAttempts());
					}
					if (uld.isAlreadyLoggedIn()) {
						throw new UserAlreadyLoggedinException(uld.getUsername());
					}
				}
			}
		});
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}