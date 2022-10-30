package com.example.web_stream_movie_be.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "name")
    private String name;
    @Column(name = "slug")
    private String slug;

    @ManyToMany(mappedBy = "categories")
    private Collection<Movie> movies;

    public String getName() {
        return this.name;
    }

    public String getSlug() {
        return this.slug;
    }
}
