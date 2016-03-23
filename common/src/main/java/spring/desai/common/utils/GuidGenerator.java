package spring.desai.common.utils;

import java.io.Serializable;

import org.springframework.cache.annotation.Cacheable;


public class GuidGenerator {

	private static GuidGenerator instance = new GuidGenerator();

	private static volatile short counter = (short) 0;

	private static int JVM = (int) 0;

	/**
	 * Singleton getInstance static method
	 * 
	 * @return instance of GuidGenerator
	 */
	public static GuidGenerator getInstance() {
		return instance;
	}

	private Serializable generateId() {

		JVM = (int) (System.currentTimeMillis() >>> 6);
		StringBuffer s = new StringBuffer(10);
		s.append(Integer.toHexString(JVM));
		s.append(format(getCounter()));

		return s.toString();
	}

	private String getGuid() throws GuidGeneratorException {
		try {
			return (String) generateId();
		} catch (RuntimeException e) {
			throw new GuidGeneratorException("Failed to generate Guid", e);
		}
	}

	public String getGuid(String name) throws GuidGeneratorException {
		try {
			if (name == null || name.trim().length() <= 2)
				return getGuid();
			return (String) generateId(name);
		} catch (Exception e) {
			throw new GuidGeneratorException("Failed to generate Guid", e);
		}
	}
	
	public static void main(String[] args) {
		
//		try {
//			GuidGenerator g = new GuidGenerator();
//			System.out.println(g.getGuid());
//		
//			System.out.println(g.getGuid("Setu Desai"));
//			System.out.println(g.getGuid("Setu Desai"));
//			System.out.println(g.getGuid("Setu Desai"));
//			System.out.println(g.getGuid("Setu Desai"));
//			System.out.println(g.getGuid("Setu Desai"));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	
		

		
	}

	private Serializable generateId(String name) {
		String s[] = name.split(" ");
		String prefix = "";
		switch (s.length) {
		case 1:
			prefix = s[0].substring(0, 2);
			break;
		case 2:
			prefix = s[0].substring(0, 1) + s[1].substring(0, 1);
			break;
		case 3:
			prefix = s[0].substring(0, 1) + s[2].substring(0, 1);
			break;
		default:
			prefix = s[0].substring(0, 2);
			break;
		}

		JVM = (int) (System.nanoTime() >>> 6);
		StringBuilder sb = new StringBuilder(10);
		sb.append(prefix.toUpperCase());
		sb.append(String.valueOf(JVM).substring(4, 10));
		System.out.println("JVM " + JVM);
		sb.append(prefix.hashCode()/100);
		return sb.toString();//.substring(0, 9);
	}

	private short getCounter() {
		synchronized (GuidGenerator.class) {
			if (counter < 0 || counter > 999)
				counter = 0;
			return counter++;
		}
	}

	@Cacheable
	private String format(short shortVal) {
		String formatted = Integer.toHexString(shortVal);
		StringBuffer buf = new StringBuffer("000");
		buf.replace(3 - formatted.length(), 3, formatted);
		return buf.toString().toUpperCase();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
