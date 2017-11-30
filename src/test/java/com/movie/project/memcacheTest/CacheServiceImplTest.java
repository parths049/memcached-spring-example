package com.movie.project.memcacheTest;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.movie.project.CacheServiceImpl;
import com.movie.project.model.Recommendation;

@Configuration
@ComponentScan("com.movie.project")
public class CacheServiceImplTest {

	//5 min
	public final static int CACHE_DURATION = 300;
	
	private CacheServiceImpl cacheService = new CacheServiceImpl();
	
	private Recommendation recommendation;
	private static final long START = 1415286463203L;
	private static final long END = 1415294605557L;

	@Before
	public void before() {
		recommendation = new Recommendation(UUID.randomUUID().toString(), START, END);
		cacheService.addToMemCache("Iman", CACHE_DURATION, recommendation);
	}
	
	@Ignore
	@Test
	public void shouldCheckRecomndValueInCache() {
		Recommendation rec = (Recommendation) cacheService.getFromMemCache("Iman");
		Assert.assertEquals(rec.uuid, recommendation.uuid);
	}
}
