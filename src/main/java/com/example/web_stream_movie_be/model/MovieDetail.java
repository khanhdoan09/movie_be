package com.example.web_stream_movie_be.model;

import com.google.gson.Gson;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String postUrl;
    private String thumbUrl;
    private String status;
    private String time;
    private String type;
    private String quality;
    private String lang;
    private int episodeTotal;
    private String episodeCurrent;
    private String content;
    @Nullable
    private int chieurap;
    private int year;
    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "movieDetail")
    private Collection<Episode> episodes;

    @ManyToMany
    @JoinTable(name = "movie_detail_actor", joinColumns = @JoinColumn(name = "movie_detail_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Collection<Actor> actors;

    @ManyToMany
    @JoinTable(name ="movie_detail_category", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<Category> categories;

    @ManyToMany
    @JoinTable(name ="movie_detail_director", joinColumns =  @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Collection<Director> directors;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
