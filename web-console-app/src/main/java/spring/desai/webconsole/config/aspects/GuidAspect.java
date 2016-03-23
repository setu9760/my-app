package spring.desai.webconsole.config.aspects;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GuidAspect {

	private static ReentrantLock lock = new ReentrantLock(true);
	private static final Logger LOGGER = Logger.getLogger(GuidAspect.class);

	@Pointcut("execution(* spring.desai.common.utils.*.*(..))")
	public void lockMethods() {
		// TODO This aspect doesn't work
	}

	@Before("lockMethods()")
	public void before() {
		try {
			LOGGER.info("obtaining lock");
			lock.lock();
			LOGGER.info("lock obtained");
		} catch (Exception e) {
			LOGGER.error("error occured while obtaining lock");
			throw new RuntimeException(e);
		}
	}

	@After("lockMethods()")
	public void after() {
		try {
			LOGGER.info("releasing lock");
			lock.unlock();
			LOGGER.info("lock released");
		} catch (Exception e) {
			LOGGER.error("error occured while releasing lock");
			throw new RuntimeException(e);
		}
	}
}
