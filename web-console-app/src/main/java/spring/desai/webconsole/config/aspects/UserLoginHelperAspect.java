package spring.desai.webconsole.config.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLoginHelperAspect {

	@Pointcut("execution(* org.springframework.security.authentication.ProviderManager.authenticate())")
	public void logUserLoginAttempt() {

	}

	@Around("logUserLoginAttempt()")
	public Object aroundUserLoginAttempt(final ProceedingJoinPoint joinpoint) throws Throwable {
		try {
			Object result = joinpoint.proceed();
			// TODO Log successful login
			return result;
		} catch (Throwable e) {
			if (e instanceof AuthenticationException) {
				// TODO figure out the cause of authentication exception and log
				// invalid login attempt
			} else {
				// TODO log any other failure and throw the exception up the
				// tree.
				throw e;
			}
		}
		return null;
	}

}
