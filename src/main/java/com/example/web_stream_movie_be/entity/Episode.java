package com.example.web_stream_movie_be.entity;

import javax.persistence.*;

@Entity
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String episodeCurrent;
    private String link;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieDetail movieDetail;

    public String getEpisodeCurrent() {
        return this.episodeCurrent;
    }

    public String getLink() {
        return this.link;
    }
}
