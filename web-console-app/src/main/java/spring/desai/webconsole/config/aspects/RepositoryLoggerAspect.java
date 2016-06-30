package spring.desai.webconsole.config.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("repo.aspect.logging")
public class RepositoryLoggerAspect {

	@Pointcut("execution(* spring.desai.common.repository.*.*.*(..))")
	public void logPointCut(){
		
	}
	
	@Before("logPointCut()")
	public void beforeAdvice(){
		// TODO
	}
	
	@After("logPointCut()")
	public void afterAdvice(){
		// TODO
	}
	
	@Around("logPointCut()")
	public Object aroundAdvice(final ProceedingJoinPoint joinPoint) throws Throwable{
		// TODO
		return joinPoint.proceed();
	}
	
}
