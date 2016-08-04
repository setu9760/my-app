package spring.desai.webconsole.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigProperties {
	private static final String BUNDLE_NAME = "props.config"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private ConfigProperties() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
