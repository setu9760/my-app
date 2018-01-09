package spring.desai.common.secure.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLoginHelperAspect{

	@Pointcut("execution(* spring.desai.common.secure.service.UserDetailsServiceImpl.*(..))")
	public void pointcut() {

	}

	@Around("pointcut()")
	public Object aroundUserLoginAttempt(final ProceedingJoinPoint joinpoint) throws Throwable {
		try {
			Object result = joinpoint.proceed();
			// TODO Log successful login
			return result;
		} catch (UsernameNotFoundException e) {
			throw e;
		}
	}
}
