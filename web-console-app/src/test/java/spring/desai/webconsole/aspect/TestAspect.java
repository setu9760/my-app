package spring.desai.webconsole.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

	private static final Logger LOGGER = Logger.getLogger(TestAspect.class);

	@Pointcut("execution(* spring.desai.webconsole.test.*.test* (..))")
	public void logMethods() {

	}

	@Before("logMethods()")
	public void beforeMethod(final JoinPoint joinPoint) {
		LOGGER.info("Entering method: " + joinPoint.getSignature().toString());
	}

	@After("logMethods()")
	public void afterMethod(final JoinPoint joinPoint) {
		LOGGER.info("Exiting method: " + joinPoint.getSignature().toString());
	}
}
