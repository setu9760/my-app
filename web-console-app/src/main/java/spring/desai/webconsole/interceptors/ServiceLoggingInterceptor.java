package spring.desai.webconsole.interceptors;
import static spring.desai.common.interceptors.InterceptorUtils.defaultAfterReturnLogging;
import static spring.desai.common.interceptors.InterceptorUtils.defaultAroundTimeLogging;
import static spring.desai.common.interceptors.InterceptorUtils.defaultBeforeLogging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("enableServiceLogging")
public class ServiceLoggingInterceptor {

	@Pointcut("execution(public * spring.desai.common.service.*.*(..))")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void beforeMethod(final JoinPoint jp) {
		defaultBeforeLogging(jp);
	}

	@Around("pointcut()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		return defaultAroundTimeLogging(joinPoint);
	}

	@AfterReturning(pointcut = "pointcut()", returning = "retValue")
	public void afterMethod(final JoinPoint jp, Object retValue) {
		defaultAfterReturnLogging(jp, retValue);
	}
}
