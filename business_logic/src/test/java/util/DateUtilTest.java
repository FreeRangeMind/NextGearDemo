package util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {
	
	@Test
	public void testParseISO8601DateTimeStandardFormat1(){
		String date1 = "2008-02-15 17:15:54";
		String dateToStringExpected = "Fri Feb 15 17:15:54 EST 2008";
		Date dateTimeStandardFormat = DateUtil.parseISO8601DateTimeStandardFormat(date1);
		Assert.assertEquals(dateTimeStandardFormat.toString(), dateToStringExpected);
	}
	
	/**
	 * Show that leading zeros are not required
	 */
	@Test
	public void testParseISO8601DateTimeStandardFormat2(){
		Date leadingZeroDate = DateUtil.parseISO8601DateTimeStandardFormat("2008-02-07 01:06:08");
		Date noLeadingZeroDate = DateUtil.parseISO8601DateTimeStandardFormat( "2008-2-7 1:6:8");
		Assert.assertEquals(noLeadingZeroDate, leadingZeroDate);
	}

	/**
	 * Show that leading zeros are not required
	 */
	@Test
	public void testParseISO8601DateTimeStandardFormat3(){
		Date leadingZeroDate = DateUtil.parseISO8601DateTimeStandardFormat("2008-02-07 01:06:08");
		Date noLeadingZeroDate = DateUtil.parseISO8601DateTimeStandardFormat( "2008-02-07 1:06:08");
		Assert.assertEquals(noLeadingZeroDate, leadingZeroDate);
	}
	
	/**
	 * Show that exception is thrown if date only field is given to date time function.
	 */
	@Test
	public void testParseISO8601DateTimeStandardFormat4(){
		
		boolean isError = false;
		
		try {
			DateUtil.parseISO8601DateTimeStandardFormat("2008-02-07");
		} catch (RuntimeException e) {
			isError = true;
		}
		
		if ( !(isError) ) {
			Assert.fail("Failed to error with invalid date input.");
		}
	}
	
	@Test
	public void testParseISO8601DateStandardFormat1(){
		String date1 = "2008-02-15";
		String dateToStringExpected = "Fri Feb 15 00:00:00 EST 2008";
		Date dateStandardFormat = DateUtil.parseISO8601DateStandardFormat(date1);
		Assert.assertEquals(dateStandardFormat.toString(), dateToStringExpected);
	}

	/**
	 * Show that leading zeros are not required
	 */
	@Test
	public void testParseISO8601DateStandardFormat2(){
		Date leadingZeroDate = DateUtil.parseISO8601DateStandardFormat("2008-02-07");
		Date noLeadingZeroDate = DateUtil.parseISO8601DateStandardFormat( "2008-2-7");
		Assert.assertEquals(noLeadingZeroDate, leadingZeroDate);
	}

	/**
	 * Show that time is stripped if date and time field is given to date only function.
	 */
	@Test
	public void testParseISO8601DateStandardFormat3(){

		String date1 = "2008-02-15 17:15:54";
		String dateToStringExpected = "Fri Feb 15 00:00:00 EST 2008";
		Date dateTimeStandardFormat = DateUtil.parseISO8601DateStandardFormat(date1);
		Assert.assertEquals(dateTimeStandardFormat.toString(), dateToStringExpected);
	}

	@Test
	public void testIsValidDate1(){
		Assert.assertTrue( DateUtil.isValidDate("2008-02-07") );
	}

	@Test
	public void testIsValidDate2(){
		Assert.assertTrue( DateUtil.isValidDate("2008-2-7") );
	}

	@Test
	public void testIsValidDate3(){
		Assert.assertTrue( DateUtil.isValidDate("2008-02-07 1:06:08") );
	}

	@Test
	public void testIsValidDate4(){
		Assert.assertTrue( DateUtil.isValidDate("2008-02-07 01:06:08") );
	}
	@Test
	public void testIsValidDate5(){
		Assert.assertTrue( DateUtil.isValidDate("2008-2-7 1:6:8") );
	}
	/**
	 * Show that 6-digit dates are not considered valid.  
	 * 
	 * Note:  if parsed this will be considered 8 A.D., not 2008.
	 */
	@Test
	public void testIsValidDate6(){
		Assert.assertFalse( DateUtil.isValidDate("08-02-07") );
	}

	@Test
	public void testIsValidDate7(){
		Assert.assertFalse( DateUtil.isValidDate("2008/02/07") );
	}

	@Test
	public void testIsValidDate8(){
		Assert.assertFalse( DateUtil.isValidDate("02-07-2008") );
	}

	@Test
	public void testIsValidDateTime1(){
		Assert.assertTrue( DateUtil.isValidDateTime("2008-02-07 1:06:08") );
	}

	@Test
	public void testIsValidDateTime2(){
		Assert.assertTrue( DateUtil.isValidDateTime("2008-02-07 01:06:08") );
	}
	@Test
	public void testIsValidDateTime3(){
		Assert.assertTrue( DateUtil.isValidDateTime("2008-2-7 1:6:8") );
	}

	@Test
	public void testIsValidDateTime4(){
		Assert.assertTrue( DateUtil.isValidDateTime("2008-02-07 1:06:00") );
	}

	@Test
	public void testIsValidDateTime5(){
		Assert.assertTrue( DateUtil.isValidDateTime("2008-02-07 1:0:0") );
	}

	@Test
	public void testIsValidDateTime6(){
		Assert.assertFalse( DateUtil.isValidDateTime("2008-02-07 1:06") );
	}

	@Test
	public void testIsValidDateTime7(){
		Assert.assertFalse( DateUtil.isValidDateTime("2008-02-07") );
	}

	@Test
	public void testIsValidDateTime8(){
		Assert.assertFalse( DateUtil.isValidDateTime("2008-2-7") );
	}

	@Test
	public void testIsValidDateTime13(){
		Assert.assertFalse( DateUtil.isValidDateTime("2008-02-07  01:06:08") );
	}

	@Test
	public void testIsValidDateTime14(){
		Assert.assertFalse( DateUtil.isValidDateTime("2008-02-0701:06:08") );
	}

	/**
	 * Show that 6-digit dates are not considered valid.  
	 * 
	 * Note:  if parsed this will be considered 8 A.D., not 2008.
	 */
	@Test
	public void testIsValidDateTime9(){
		Assert.assertFalse( DateUtil.isValidDateTime("08-02-07") );
	}

	@Test
	public void testIsValidDateTime10(){
		Assert.assertFalse( DateUtil.isValidDateTime("2008/02/07") );
	}

	@Test
	public void testIsValidDateTime11(){
		Assert.assertFalse( DateUtil.isValidDateTime("02-07-2008") );
	}

	/**
	 * Show that 6-digit dates are not considered valid.  
	 * 
	 * Note:  if parsed this will be considered 8 A.D., not 2008.
	 */
	@Test
	public void testIsValidDateTime12(){
		Assert.assertFalse( DateUtil.isValidDateTime("08-02-07 01:06:08") );
	}

	@Test
	public void testGetISO8601DateAsString1(){
		String date1 = "2008-02-15 17:15:54";
		Date dateTimeStandardFormat = DateUtil.parseISO8601DateTimeStandardFormat(date1);
		String actualDate = DateUtil.getISO8601DateAsString( dateTimeStandardFormat );
		
		Assert.assertEquals(actualDate, "2008-02-15");
	}

	@Test
	public void testGetISO8601DateAsString2(){
		String date1 = "2008-02-15";
		Date dateTimeStandardFormat = DateUtil.parseISO8601DateStandardFormat(date1);
		String actualDate = DateUtil.getISO8601DateAsString( dateTimeStandardFormat );
		
		Assert.assertEquals(actualDate, date1);
	}

	@Test
	public void testGetISO8601DateTimeAsString1(){
		String date1 = "2008-02-15 17:15:54";
		Date dateTimeStandardFormat = DateUtil.parseISO8601DateTimeStandardFormat(date1);
		String actualDate = DateUtil.getISO8601DateTimeAsString( dateTimeStandardFormat );
		
		Assert.assertEquals(actualDate, date1);
	}

	@Test
	public void testGetISO8601DateTimeAsString2(){
		String date1 = "2008-02-15";
		Date dateTimeStandardFormat = DateUtil.parseISO8601DateStandardFormat(date1);
		String actualDate = DateUtil.getISO8601DateTimeAsString( dateTimeStandardFormat );
		
		Assert.assertEquals(actualDate, "2008-02-15 00:00:00");
	}
	
}
