package com.example.web_stream_movie_be.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;

    public String getName() {
        return this.name;
    }

    @ManyToMany(mappedBy = "actors")
    private Collection<MovieDetail> movieDetails;


}
