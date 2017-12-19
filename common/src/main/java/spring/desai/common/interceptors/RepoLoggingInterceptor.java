package spring.desai.common.interceptors;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Aspect
@Configuration
@Profile("enableRepoLogging")
public class RepoLoggingInterceptor {

	@Pointcut("execution(public * spring.desai.common.repository.*.*(..))")
	protected void pointcut() {
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
