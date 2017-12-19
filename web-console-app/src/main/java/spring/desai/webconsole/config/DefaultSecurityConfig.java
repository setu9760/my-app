package spring.desai.webconsole.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import spring.desai.common.repository.UsrrRepository;
import spring.desai.common.secure.repository.impl.jdbc.UsrrRepositoryImpl;

@Order(100)
@Configuration
@Profile("!secure")
@EnableWebSecurity
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public UsrrRepository getUsrrRepository(){
		return new UsrrRepositoryImpl();
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("rest").password("password").roles("REST_USER").and()
				.withUser("admin123").password("password").roles("USER", "ADMIN", "ACTUATOR").and()
				.withUser("test123").password("password").roles("REST_USER").and()
				.withUser("dis").password("password").roles("REST_USER").disabled(true);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CsrfTokenResponseHeaderBindingFilter csrfTokenFilter = new CsrfTokenResponseHeaderBindingFilter();
		http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);

		http.authorizeRequests().antMatchers("/rest/**").access("hasRole('ROLE_REST_USER')")
				.and().formLogin().successHandler(new AjaxAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler())).failureUrl("/login?error=1").permitAll()
				.and().exceptionHandling().accessDeniedPage("/403")
//				.and().httpBasic()
				.and().csrf();
	}
	
	/**
	 * Binds a {@link org.springframework.security.web.csrf.CsrfToken} to the {@link HttpServletResponse}
	 * headers if the Spring {@link org.springframework.security.web.csrf.CsrfFilter} has placed one in the {@link HttpServletRequest}.
	 *
	 * Based on the work found in: <a href="http://stackoverflow.com/questions/20862299/with-spring-security-3-2-0-release-how-can-i-get-the-csrf-token-in-a-page-that">Stack Overflow</a>
	 *
	 * @author Allan Ditzel
	 * @since 1.0
	 */
	static class CsrfTokenResponseHeaderBindingFilter extends OncePerRequestFilter {
	    protected static final String REQUEST_ATTRIBUTE_NAME = "_csrf";
	    protected static final String RESPONSE_HEADER_NAME = "X-CSRF-HEADER";
	    protected static final String RESPONSE_PARAM_NAME = "X-CSRF-PARAM";
	    protected static final String RESPONSE_TOKEN_NAME = "X-CSRF-TOKEN";

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, javax.servlet.FilterChain filterChain) throws ServletException, IOException {
	        CsrfToken token = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);

	        if (token != null) {
	            response.setHeader(RESPONSE_HEADER_NAME, token.getHeaderName());
	            response.setHeader(RESPONSE_PARAM_NAME, token.getParameterName());
	            response.setHeader(RESPONSE_TOKEN_NAME , token.getToken());
	        }

	        filterChain.doFilter(request, response);
	    }
	}
	
	/**
	 *
	 * Authentication success handler for integration with SPA applications that need to login using Ajax instead of
	 * a form post.
	 *
	 * Detects if its a ajax login request, and if so sends a customized response in the body, otherwise defaults
	 * to the existing behaviour for none-ajax login attempts.
	 *
	 */
	static class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	    private AuthenticationSuccessHandler defaultHandler;

	    public AjaxAuthenticationSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
	        this.defaultHandler = defaultHandler;
	    }

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
	    		throws IOException, ServletException {

	        if ("true".equals(request.getHeader("X-Login-Ajax-call"))) {
	            response.getWriter().print("ok");
	            response.getWriter().flush();
	        }
	        else {
	            defaultHandler.onAuthenticationSuccess(request, response, authentication);
	        }

	    }
	}

}
