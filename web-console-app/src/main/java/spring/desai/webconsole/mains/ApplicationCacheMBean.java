package spring.desai.webconsole.mains;

public interface ApplicationCacheMBean {
	
	int getMaxCacheSize();
	void setMaxCacheSize(int value);
	int getCachedObjects();
	void clearCache();
	
}
