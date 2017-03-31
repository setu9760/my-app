package spring.desai.common.repository.impl.jdbc;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Aspect
@Component
public class RepositoryExceptionInterceptor {

	private static final Logger log = Logger.getLogger(RepositoryExceptionInterceptor.class);
	
	@Pointcut("execution(public * spring.desai.common.repository.impl.jdbc.*.*(..))")
	public void exceptionPointcut() {
	}

	@Around("exceptionPointcut()")
	public Object aroundAdvice(final ProceedingJoinPoint object) throws Throwable {
		try {
			return object.proceed();
		} catch (DataAccessException e) {
			log.error("error occured while performing repository operations", e);
			throw new RepositoryDataAccessException(e);
		}
	}
}
