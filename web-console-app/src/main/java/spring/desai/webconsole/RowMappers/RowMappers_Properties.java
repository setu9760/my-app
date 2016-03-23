package spring.desai.webconsole.RowMappers;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class RowMappers_Properties {
	private static final String BUNDLE_NAME = "props.rowmappers"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private RowMappers_Properties() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
