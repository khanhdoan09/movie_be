package com.example.web_stream_movie_be.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;

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
