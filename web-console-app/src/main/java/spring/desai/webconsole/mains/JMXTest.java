package spring.desai.webconsole.mains;

import java.lang.management.ManagementFactory;
import javax.management.*;
 
public class JMXTest {
	public static void main(String[] args) throws Exception {
		ApplicationCache cache = new ApplicationCache();
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("spring.desai.webconsole.mains:type=ApplicationCacheMBean");		
		mbs.registerMBean(cache, name);
		imitateActivity(cache);
	}
	private static void imitateActivity(ApplicationCache cache) {
		while(true) {
			try {
				cache.cacheObject(new Object());
				Thread.sleep(1000);
			}
			catch(InterruptedException e) { }
		}
	}
}