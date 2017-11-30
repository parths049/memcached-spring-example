package com.movie.project.filters;

import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.movie.project.model.Recommendation;

@Configuration
@ComponentScan("com.movie.project")
public class PercentCompleteFilterTest {

	private final PercentCompleteFilter percentCompleteFilter = new PercentCompleteFilter();
	
	private Recommendation recommendation;
	private static final long START = 1415286463203L;
	private static final long END = 1415294605557L;
	
	@Before
	public void doBefore() {
		recommendation = new Recommendation(UUID.randomUUID().toString(), START, END);
	}
	
	/**
	 * should check method for is relevent
	 */
	@Test
	public void shoudCheckIsRelevant() {
		Assert.assertTrue(percentCompleteFilter.isRelevant(recommendation, START, END));
	}
}
