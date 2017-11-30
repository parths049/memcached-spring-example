package com.movie.project;

import com.movie.project.filters.RecFilter;
import com.movie.project.model.Recommendation;
import com.movie.project.model.Recommendations;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceEngine {

    private static final int MAX_LOOPS = 500;
    private static final int MIN_DURATION = 30;
    private static final int RANDOM_DURATION = 90;
    private static final int MAX_BEGIN_END = 300;
    public final static int PERCENTAGE = 60;
    
    private Random random = new Random(System.currentTimeMillis());

    private RecFilter[] filters;

    @Autowired
    public ServiceEngine(RecFilter[] filters) {
        this.filters = filters;
    }

    public Recommendations recommend(long numberOfRecs, long start, long end) {

        List<Recommendation> recs = new ArrayList<Recommendation>();
        int loops = 0;
        do {
            Recommendation r = generate();
            if (runFilters(r, start, end)) {
                recs.add(r);
            }
        } while (recs.size() < numberOfRecs && ++loops < MAX_LOOPS);
        
        
        return new Recommendations(recs);
    }

    public Recommendation generate() {
        // generate random recommendation, with random start and end time
        DateTime start = randomStartTime();
        DateTime end = start.plus(randomDuration());
        return new Recommendation(UUID.randomUUID().toString(), start.getMillis(), end.getMillis());
    }

    private DateTime randomStartTime() {
        DateTime now = DateTime.now();
        return random.nextBoolean() ? now.plusMinutes(random.nextInt(MAX_BEGIN_END)) : now.minusMinutes(random.nextInt(MAX_BEGIN_END));
    }

    private Duration randomDuration() {
        return Duration.standardMinutes(MIN_DURATION + random.nextInt(RANDOM_DURATION));
    }

    private boolean runFilters(Recommendation r, long start, long end) {
        for (RecFilter f: filters) {
            if (!f.isRelevant(r, start, end)) {
                return false;
            }
        }
        return true;
    }
}
