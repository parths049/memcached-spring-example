package com.movie.project.util;

import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.movie.project.model.Recommendation;

public class GeneralHelperTest {
	
	private Recommendation recommendation;
	private static final long START = 1415286463203L;
	private static final long END = 1415294605557L;
	
	@Before
	public void doBefore() {
		recommendation = new Recommendation(UUID.randomUUID().toString(), START, END);
	}
	
	/**
	 * check the difference is grater then percent then return ture.
	 * @throws Exception
	 */
	@Test
    public void shouldCheckTimeDifIsGraterThenPercentOrNot() throws Exception {
    	boolean expectTrue = GeneralHelper.convertTimeToPercentage(recommendation,START,END);
    	Assert.assertNotNull(expectTrue);
    	Assert.assertTrue(expectTrue);
    }
	
	/**
	 * check the difference is less then percent then return false.
	 * @throws Exception
	 */
	@Test
    public void shouldCheckTimeDifIsLessThenPercentOrNot() throws Exception {
    	boolean expectFalse = GeneralHelper.convertTimeToPercentage(recommendation,END,START);
    	Assert.assertNotNull(expectFalse);
    	Assert.assertFalse(expectFalse);
    }
	
	/**
	 * check the test case for an exception
	 */
	@Test
	public void shoudlHandleExceptionForConvertTimeToPercentage() {
		try {
			GeneralHelper.convertTimeToPercentage(null,0,0);
		} catch (Exception exception) {
			Assert.assertTrue(exception instanceof NullPointerException);
		    Assert.fail("NullPointerException is thrown !");
		}
	}
}
