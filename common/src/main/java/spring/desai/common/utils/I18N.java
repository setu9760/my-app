package spring.desai.common.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * internationalisation service
 */
public class I18N {

    private static final String BUNDLE_NAME = "messages";

    private static ResourceBundle RESOURCE_BUNDLE;

    static {
        try {
            RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
        } catch (Exception e) {
            System.out.println("Error loading resource bundle " + BUNDLE_NAME);
        }
    }

    /**
     * use static accessors
     */
    private I18N() {
        // Empty by design
    }

    /**
     * @param key
     * @return internationalisable string
     * @refactorit.skip finalize_locals
     */
    public static String getString(final String key) {
        String retVal;

        if (key==null)
        	return null;
        
        if (RESOURCE_BUNDLE == null) {
            retVal = '!' + key + '!'; //if running from a unit test we don't find messages.properties
        } else {
            try {
                retVal = RESOURCE_BUNDLE.getString(key);
            } catch (MissingResourceException e) {
                retVal = '!' + key + '!';
            }
        }

        return retVal;
    }
    
    public static String getNoArgString(final String key){
    	 String retVal;

         if (RESOURCE_BUNDLE == null) {
             retVal = '!' + key + '!'; //if running from a unit test we don't find messages.properties
         } else {
             try {
                 retVal = RESOURCE_BUNDLE.getString(key);
             } catch (MissingResourceException e) {
                 retVal = '!' + key + '!';
             }
         }
         if (retVal.contains("{")) {
        	 retVal = retVal.substring(0, retVal.indexOf("{"));
		}
        return retVal;
    }

    /**
     * TODO: translate (EV)
     * FIXME: thus replace as in ButtonFactory.createTranslatedButton
     * 
     * @param key
     * @return internationalized character. Usually used for mnemonics.
     */
    public static char getChar(final String key) {
        char result;

        String str = getString(key);
        if (str == null) {
            result = '\0';
        } else {
            str = str.trim();
            
            if (str.length() < 1 || str.charAt(0) == '!') {
                result = '\0';
            } else {
                result = str.charAt(0);
            }
        }

        return result;
    }

    /**
     * @param key
     * @param values
     * @return internationalized string with replacement of placeholders by values
     */
    public static String getString(final String key, Object[] values) {
        String value = getString(key);

        String output;
        if (value.length() == 0 || value.charAt(0) != '!') {
            // found message
            output = MessageFormat.format(value, values);
        } else {
            // even if we can't find the string make sure the correct values are dumped out
            StringBuffer buffer = new StringBuffer(value + " : ");
            if (values != null) {
                for (int i = 0, n = values.length; i < n; ++i) {
                    buffer.append(values[i] + " ");
                }
            }
            output = buffer.toString();
        }

        return output;
    }

	/**
	 * @param key
	 * @param arg
	 * @return internationalized string with replacement of placeholders by values
	 */
	public static String getString(String key, Object arg) {
		return getString(key, new Object[] {arg});
	}
}