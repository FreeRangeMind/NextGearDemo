package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {

	//ISO 8601 date standard format
	public static final String ISO_8601_DATE_STANDARD_FORMAT = "yyyy-MM-dd";
	
	//ISO 8601 date and time standard format
	public static final String ISO_8601_DATE_TIME_STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final long MILLISECONDS_PER_SECOND = 1000;
	
	public static final long MILLISECONDS_PER_MINUTE = MILLISECONDS_PER_SECOND * 60;
	
	public static final long MILLISECONDS_PER_HOUR = MILLISECONDS_PER_MINUTE * 60;
	
	public static final long MILLISECONDS_PER_DAY = MILLISECONDS_PER_HOUR * 24;

	//Note:  2-digit year is not allowed, century must be provided
	private static final Pattern VALID_DATE = Pattern.compile("\\d{4}+-\\d{1,2}+-\\d{1,2}+");
	private static final Pattern VALID_DATE_TIME = Pattern.compile("\\d{4}+-\\d{1,2}+-\\d{1,2}+ \\d{1,2}+:\\d{1,2}+:\\d{1,2}+");

	/**
	 * This method returns whether the given date (as string) is a valid ISO 8601 formatted date.
	 * 
	 * @param dateAsString
	 * @return
	 */
	public static boolean isValidDate( String dateAsString ) {
		
		return (VALID_DATE.matcher( dateAsString ).matches() || VALID_DATE_TIME.matcher( dateAsString ).matches());
	}
	
	/**
	 * This method returns whether the given date (as string) is a valid ISO 8601 formatted date and time.
	 * 
	 * @param dateAsString
	 * @return
	 */
	public static boolean isValidDateTime( String dateAsString ) {
		
		return VALID_DATE_TIME.matcher( dateAsString ).matches();
	}

	/**
	 * Use this to get a date in <code>ms</code> milliseconds offset from
	 * the current date/time.
	 * 
	 * @param ms the number of milliseconds to offset
	 * @return a date <code>ms</code> offset by the number milliseconds
	 */
	public static Date getDate( long ms ) {

		return new Date( new Date().getTime() + ms );
	}

	/**
	 * Convenient method that will parse the date input against the ISO 8601 Date 
	 * Standard format: yyyy-MM-dd
	 *  
	 * @param dateAsString
	 * @return Date object
	 */
	public static Date parseISO8601DateStandardFormat(String dateAsString){
		return parseString2Date(dateAsString, ISO_8601_DATE_STANDARD_FORMAT); 
	}
	
	/**
	 * Convenient method that will parse the date input against the ISO 8601 Date & Time 
	 * Standard format: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateAsString
	 * @return Date Object
	 */
	public static Date parseISO8601DateTimeStandardFormat(String dateAsString){
		return parseString2Date(dateAsString, ISO_8601_DATE_TIME_STANDARD_FORMAT); 
	}

	/**
	 * Convenient method that parse a String date and return a java.util.Date object
	 *
	 * @param dateAsString
	 * @param pattern
	 * @return Date object or null
	 */
	public static Date parseString2Date(String dateAsString, String pattern){
		
		DateFormat dateFormat = new SimpleDateFormat( pattern );
		Date dateValue = null;
		try {
			dateValue = dateFormat.parse( dateAsString );
		} catch (ParseException pe) {
			throw new RuntimeException("Can not resolve  Date: " + dateAsString + " with pattern: "+ pattern, pe);
		}
		return dateValue;
	}

	/**
	 * Convenient method that will return the ISO 8601 Date string value for the given date.
	 *  
	 * Standard format: yyyy-MM-dd
	 * 
	 * @param dateValue
	 * @return 
	 */
	public static String getISO8601DateAsString( Date dateValue ){
		
		return getISO8601DateStringForDate(dateValue, ISO_8601_DATE_STANDARD_FORMAT); 
	}

	/**
	 * Convenient method that will return the ISO 8601 Date & Time string value for the given date.
	 *  
	 * Standard format: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateValue
	 * @return 
	 */
	public static String getISO8601DateTimeAsString( Date dateValue ){
		
		return getISO8601DateStringForDate(dateValue, ISO_8601_DATE_TIME_STANDARD_FORMAT); 
	}
	
	private static String getISO8601DateStringForDate( Date dateValue, String pattern ) {
		
		DateFormat dateFormat = new SimpleDateFormat( pattern );
		
		return dateFormat.format( dateValue );
	}
	
}
