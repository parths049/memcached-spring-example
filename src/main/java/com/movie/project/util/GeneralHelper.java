package com.movie.project.util;

import java.util.concurrent.TimeUnit;

import com.movie.project.ServiceEngine;
import com.movie.project.model.Recommendation;

public class GeneralHelper {
	
	/**
	 * convert time to Percentage
	 * @param Recommendation
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean convertTimeToPercentage(Recommendation r , long start, long end) {
		try {
			long percent = TimeUnit.MILLISECONDS.toMinutes(r.end - r.start) * 100 / TimeUnit.MILLISECONDS.toMinutes(end - start);
			return (percent < ServiceEngine.PERCENTAGE) ? false : true;
		} catch (Exception exception) {
			return false;
		}
	}
}
