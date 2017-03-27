package spring.desai.webconsole.service.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.exception.ServiceException;

@Aspect
@Component
public class ServiceExceptionInterceptor {

	@Pointcut("execution(public * spring.desai.webconsole.service.impl.*.*(..))")
	public void exceptionPointcut() {

	}

	@Around("exceptionPointcut()")
	public Object aroundAdvice(final ProceedingJoinPoint object) throws Throwable {
		try {
			return object.proceed();
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException(object.toShortString(), e);
		}
	}

}
