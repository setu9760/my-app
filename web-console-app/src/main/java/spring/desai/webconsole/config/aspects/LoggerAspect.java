package spring.desai.webconsole.config.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	private static final Logger LOGGER = Logger.getLogger("aspectLogger");

	@Pointcut("execution(* spring.desai.common.dao.*.*(..))")
	public void logMethods() {

	}

	@Before("logMethods()")
	public void beforeMethod(final JoinPoint joinPoint) {
		LOGGER.info("Entering method " + joinPoint.getSignature().toString());
	}

	@Around("logMethods()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) {
		try {
			long start = System.nanoTime();
			Object result = joinPoint.proceed();
			long end = System.nanoTime();
			LOGGER.info(String.format("%s took %d nanoSeconds", joinPoint.getSignature().toLongString(), (end - start)));
			return result;
		} catch (Throwable t) {
			LOGGER.error(t);
		}
		return null;
	}

	@After("logMethods()")
	public void afterMethod(final JoinPoint joinPoint) {
		LOGGER.info("Exiting method " + joinPoint.getSignature().toString());
	}
}
