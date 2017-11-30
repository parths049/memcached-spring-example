package com.movie.project.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class Recommendations implements Serializable {
	
	private static final long serialVersionUID = -6053449103876352190L;
	
	public final List<Recommendation> recommendations;

    private Recommendations() {
        this(null);
    }

    public Recommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}
