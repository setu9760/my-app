package spring.desai.webconsole.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@Profile("secure")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService)/*.passwordEncoder(new BCryptPasswordEncoder())*/;
		
//		auth.inMemoryAuthentication()
//		.withUser("test123").password("password").roles("USER").and()
//		.withUser("admin123").password("password").roles("USER", "ADMIN");
//	
//		auth.userDetailsService(new UserDetailsService() {
//
//		@Override
//		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//			if (username == null || username.isEmpty()) {
//				throw new UsernameNotFoundException(username);
//			}
//
//			List<GrantedAuthority> authorities = new ArrayList<>();
//			if (username.equals("test123")) {
//				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//			} else if (username.equals("admin123")) {
//				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//			} else if (username.startsWith("locked")){ 
//				return new User(username, "password", true, true, true, false, authorities);
//			} else if (username.startsWith("disabled")) { 
//				return new User(username, "password", false, true, true, true, authorities);
//			} else if (username.startsWith("accexpired")) { 
//				return new User(username, "password", true, false, true, true, authorities);
//			} else if (username.startsWith("credexpired")) { 
//				return new User(username, "password", true, true, false, true, authorities);
//			} else if (username.startsWith("trial")) {
//				throw new UsernameNotFoundException("User " + username + " not found in databse. Double check the username and try again.");
//			}  else {
//				authorities.add(new SimpleGrantedAuthority("ROLE_UNAUTHORISED"));
//			} 
//			
//			return new User(username, "password", authorities);
//		}
//		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		 CsrfTokenResponseHeaderBindingFilter csrfTokenFilter = new CsrfTokenResponseHeaderBindingFilter();
	     http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);
		
		http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.and().authorizeRequests().antMatchers("/**").access("hasRole('ROLE_USER')")
				.and().formLogin().loginPage("/login").successHandler(new AjaxAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler())).failureUrl("/login?error=1").permitAll()/**.failureUrl("/login?error").usernameParameter("username").passwordParameter("password")*/
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
//				.and().exceptionHandling().accessDeniedPage("/403.jsp")
				.and().exceptionHandling().accessDeniedPage("/403")
				.and().csrf()
				.and().httpBasic();
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
	class CsrfTokenResponseHeaderBindingFilter extends OncePerRequestFilter {
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
	class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

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
