package com.movie.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.movie.project.model.Recommendations;


@Controller
@RequestMapping("/service")
public class ServiceController extends WebMvcConfigurerAdapter  {
	
	//5 min
	public final static int CACHE_DURATION = 300;
	
    private ServiceEngine serviceEngine;
    
    private CacheService cacheService;

    @Autowired
    public ServiceController(ServiceEngine serviceEngine, CacheService cacheService) {
        this.serviceEngine = serviceEngine;
        this.cacheService = cacheService;
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    @ResponseBody
    public Recommendations getUserRecommendations(@RequestParam("num") Long numberOfRecs,
                                                  @RequestParam("start") Long start,
                                                  @RequestParam("end") Long end,
                                                  @RequestParam("subscriber") String subscriberId) {
		//check subscriberId is present in memcache then get form the cache
    	if(subscriberId != null && cacheService.getFromMemCache(subscriberId) != null) {
			return cacheService.getRecommandationsFromCache(subscriberId);
		//check subscriberId is not present in memcache then store in to the cache
    	} else {
			Recommendations recommandations = serviceEngine.recommend(numberOfRecs, start, end);
			cacheService.addToMemCache(subscriberId, CACHE_DURATION, recommandations);
			return recommandations;
		}
    }
}
