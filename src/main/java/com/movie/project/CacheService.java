package com.movie.project;

import net.spy.memcached.internal.OperationFuture;

import com.movie.project.model.Recommendations;

public interface CacheService {
	
	/**
	 * method to retrieve data from MemCached Server
	 * @param subscriberId
	 * @return
	 */
	public Object getFromMemCache(String subscriberId);
	
	/**
	 * 
	 * @param subscriberId
	 * @param expTime
	 * @param value
	 * @return
	 */
	public OperationFuture<Boolean> addToMemCache(String subscriberId, int expTime, Object value);
	
	/**
	 * 
	 */
	public void closeMemCacheClient();
	
	/**
	 * 
	 * @param subscriberId
	 */
	public void cleanupCache(String subscriberId);	
	
	/**
	 * 
	 * @param subscriberId
	 * @return
	 */
	public Recommendations getRecommandationsFromCache(String subscriberId);
  
 }
