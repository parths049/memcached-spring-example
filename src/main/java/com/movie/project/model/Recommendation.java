package com.movie.project.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recommendation1")
public class Recommendation implements Serializable {

   private static final long serialVersionUID = 5969710449984908556L;

	@XmlElement
    public final String uuid;

    @XmlElement
    public final Long start;

    @XmlElement
    public final Long end;

    private Recommendation() {
        this(null, null, null);
    }

    public Recommendation(String uuid, Long start, Long end) {
        this.uuid = uuid;
        this.start = start;
        this.end = end;
    }
}
