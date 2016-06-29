package spring.desai.webconsole.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.google.gson.Gson;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		 if ("application/json".equals(request.getHeader("Content-Type"))) {
			 try {
				 
				StringBuffer sb = new StringBuffer();
				String line = null;

                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null){
                    sb.append(line);
                }
                // Fetch username and password parameter from the json string and either create custom request attribute or 
                // set them as parameter in existing request before invoking super.attemptAuthenitcation.
                // TODO 
                Gson gson = new Gson();
                LoginParams params = gson.fromJson(sb.toString(), LoginParams.class);
                Map<String, String[]> credentials = new TreeMap<>();
                credentials.put("userName", new String[] {params.userName});
                credentials.put("password", new String[] {params.password});
                LoginRequestWrapper requestWrapper = new LoginRequestWrapper(request, credentials);
                return super.attemptAuthentication(requestWrapper, response);
                
			 } catch (IOException e) {
				throw new AuthenticationServiceException("Error occured while performing authentication.", e);
			}
			
		}
		
		return super.attemptAuthentication(request, response);
	}

	class LoginParams implements Serializable {
		private static final long serialVersionUID = 1234523423432667L;
		String userName;
		String password;

		public LoginParams() {
		}

		public String getPassword() {
			return password;
		}

		public String getUserName() {
			return userName;
		}
	}

	class LoginRequestWrapper extends HttpServletRequestWrapper {

		private final Map<String, String[]> modifiableParameters;
		private Map<String, String[]> allParameters = null;

		public LoginRequestWrapper(HttpServletRequest request, final Map<String, String[]> additionalParams) {
			super(request);
			modifiableParameters = new TreeMap<String, String[]>();
			modifiableParameters.putAll(additionalParams);
		}

		@Override
		public String getParameter(final String name) {
			String[] strings = getParameterMap().get(name);
			if (strings != null) {
				return strings[0];
			}
			return super.getParameter(name);
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			if (allParameters == null) {
				allParameters = new TreeMap<String, String[]>();
				allParameters.putAll(super.getParameterMap());
				allParameters.putAll(modifiableParameters);
			}
			// Return an unmodifiable collection because we need to uphold the
			// interface contract.
			return Collections.unmodifiableMap(allParameters);
		}

		@Override
		public Enumeration<String> getParameterNames() {
			return Collections.enumeration(getParameterMap().keySet());
		}

		@Override
		public String[] getParameterValues(final String name) {
			return getParameterMap().get(name);
		}

	}
}
