package com.movie.project.filters;

import org.springframework.stereotype.Component;

import com.movie.project.model.Recommendation;
import com.movie.project.util.GeneralHelper;

@Component
public class PercentCompleteFilter implements RecFilter {

	public boolean isRelevant(Recommendation r, long start, long end) {
		return GeneralHelper.convertTimeToPercentage(r, start, end);
	}
}
