package com.movie.project.filters;

import com.movie.project.model.Recommendation;

public interface RecFilter {

    public boolean isRelevant(Recommendation r, long start, long end);
}
