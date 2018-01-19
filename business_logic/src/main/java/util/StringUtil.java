package util;

public class StringUtil {

	/**
	 * Simple utility method to determine if a string is null or empty.
	 * 
	 * @param string
	 * @return <code>true</code> if the string is either <code>null</code> or
	 *         an empty string, otherwise <code>false</code>.
	 */
	public static boolean isNullOrEmpty( String string ) {
		
		return (string == null || string.isEmpty());
	}

}
