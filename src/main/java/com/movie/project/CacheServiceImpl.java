package com.movie.project;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.project.model.Recommendations;

@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	MemcachedClient memcacheClient = null;
	
	//Define how much time the get() method will wait for data
    public final static int WAITTIME = 5;
    
	private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);    
	
	//method to add data in MemCached Server
    public OperationFuture<Boolean> addToMemCache(String subscriberId, int expTime, Object value) {
        OperationFuture<Boolean> status = memcacheClient.set(subscriberId, expTime, value);
        return status;
    }
 
    public Object getFromMemCache(String subscriberId) {
 
    	Future<Object> f = null;
    	
    	Object obj = null;
    	
        f = memcacheClient.asyncGet(subscriberId);
        try {
            obj = f.get(WAITTIME, TimeUnit.SECONDS);
 
        } 
        catch (TimeoutException e) {
            f.cancel(false);
            logger.error("TimeoutException >>>" + e);
        }
        catch (InterruptedException ex) {
        	logger.error("InterruptedException >>>" + ex);
        } 
        catch (ExecutionException ex) {
        	logger.error("ExecutionException >>>" + ex);
        }
        return obj;
    }
 
    public void closeMemCacheClient() {
        memcacheClient.shutdown();
    }
    
    public void cleanupCache(String subscriberId) {
    	memcacheClient.delete(subscriberId);
    }
    
    public Recommendations getRecommandationsFromCache(String subscriberId){
    	Recommendations recommendations = null;
    	recommendations = (Recommendations)getFromMemCache(subscriberId);
    	return recommendations;
    }
    
}
