package spring.desai.common.interceptors;

import static java.lang.String.format;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public final class InterceptorUtils {

	public static void defaultBeforeLogging(final JoinPoint jp) {
		Logger log = getLog(jp);
		if (log.isInfoEnabled()) {
			Method met = getMethod(jp, log);

			// Create the log string with the method name
			StringBuilder builder = new StringBuilder("Calling: ");
			builder.append(met.getName()).append("(");
			boolean hasArgs = false;
			for (Object o : jp.getArgs()) {
				if (o != null) {
					hasArgs = true;
					builder.append("<").append(o.getClass().getSimpleName()).append("> ");
					builder.append(o.toString());
					try {
						builder.append(", ");
					} catch (NullPointerException e) {
						builder.append("null, ");
					}
				}
			}

			if (hasArgs) {
				builder.substring(0, builder.length() - 2);
			}
			builder.append(")");
			log.info(builder.toString());
		}
	}

	public static void defaultAfterReturnLogging(final JoinPoint jp, final Object retValue) {
		Logger log = getLog(jp);
		logReturn(jp, retValue, log, false);
	}

	public static Object defaultAroundTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		Object result = joinPoint.proceed();
		long end = System.nanoTime();
		getLog(joinPoint).info(format("%s took %d nanoSeconds", joinPoint.getSignature().toLongString(), (end - start)));
		return result;
	}

	public static void logException(JoinPoint jp, Throwable ex) {
		Logger log = getLog(jp);
		log.error(ex.getMessage());
		logReturn(jp, null, log, true);
	}

	public static void logReturn(JoinPoint jp, Object retValue, Logger log, boolean exceptionOccured) {
		if (log.isInfoEnabled()) {
			Method met = getMethod(jp, log);
			if (retValue == null && exceptionOccured)
				log.warn(format("Completed %s with exception", met.getName()));
			else 
				log.info(format("Completed %s with returnValue: %s", met.getName(), retValue));
		}
	}

	@SuppressWarnings("unchecked")
	private static Method getMethod(JoinPoint jp, Logger log) {
		Method invoked = null;
		try {
			MethodSignature met = (MethodSignature) jp.getSignature();
			invoked = jp.getSourceLocation().getWithinType().getMethod(met.getMethod().getName(),
					met.getMethod().getParameterTypes());
		} catch (NoSuchMethodException e) {
			log.error("Unable to get the method for logging");
		}
		return invoked;
	}

	public static Logger getLog(JoinPoint jp) {
		return Logger.getLogger(jp.getTarget().getClass());
	}
}
