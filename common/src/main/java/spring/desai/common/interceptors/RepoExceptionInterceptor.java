package spring.desai.common.interceptors;

import static spring.desai.common.interceptors.InterceptorUtils.logException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Aspect
@Configuration
public class RepoExceptionInterceptor {

	public RepoExceptionInterceptor() {
		System.out.println("Initialising: " + getClass().getName());
	}

	@Pointcut("execution(public * spring.desai.common.repository.*.*(..))")
	public void pointcut() {
	}

	@AfterThrowing(pointcut = "pointcut()", throwing = "ex")
	public void afterThrowing(final JoinPoint jp, Throwable ex) throws Throwable {

		if (ex instanceof DataAccessException || ex instanceof DataIntegrityViolationException) {
			logException(jp, ex);
			throw new RepositoryDataAccessException(ex);
		} else {
			logException(jp, ex);
			throw ex;
		}
	}
}
