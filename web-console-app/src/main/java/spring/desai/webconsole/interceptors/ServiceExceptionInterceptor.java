package spring.desai.webconsole.interceptors;
import static spring.desai.common.interceptors.InterceptorUtils.getLog;
import static spring.desai.common.interceptors.InterceptorUtils.logException;
import static spring.desai.common.interceptors.InterceptorUtils.logReturn;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.exception.ServiceException;

@Aspect
@Component
public class ServiceExceptionInterceptor {
	
	@Pointcut("execution(public * spring.desai.common.service.*.*(..))")
	public void pointcut() {
	}

	@AfterThrowing(pointcut = "pointcut()", throwing = "ex")
	public void afterThrowing(final JoinPoint jp, Throwable ex) throws Throwable {

		if (ex instanceof RepositoryDataAccessException) {
			// System exceptions were logged at source
			// do not log the exception, just the return
			logReturn(jp, null, getLog(jp), true);
			throw new ServiceException(ex);
		} else {
			logException(jp, ex);
			throw ex;
		}
	}
}
