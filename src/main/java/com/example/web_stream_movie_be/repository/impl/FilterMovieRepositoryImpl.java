package com.example.web_stream_movie_be.repository.impl;

import com.example.web_stream_movie_be.entity.Category;
import com.example.web_stream_movie_be.entity.Country;
import com.example.web_stream_movie_be.entity.Movie;
import com.example.web_stream_movie_be.repository.FilterMovieRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FilterMovieRepositoryImpl implements FilterMovieRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Movie> getListMovieByFilter(String country, String year, String category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
        Root<Movie> movie = cq.from(Movie.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (country != null && country != "" && country != "all") {
            Join<Movie, Country> joinCountry = movie.join("country");
            Predicate predicateForCountry = cb.equal(joinCountry.get("slug"), country);
            predicates.add(predicateForCountry);
        }

        if (category != null && category != "" && category != "all") {
            Join<Movie, Category> joinCategory = movie.join("categories");
            Predicate predicateForCategory = cb.equal(joinCategory.get("slug"), category);
            predicates.add(predicateForCategory);
        }

        if (year != null && year != "") {
            Predicate predicateForYear = cb.equal(movie.get("year"), year);
            predicates.add(predicateForYear);
        }

        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
        cq.where(finalPredicate);
        TypedQuery<Movie> query = em.createQuery(cq);

        List<Movie> listMovie = query.getResultList();
        return listMovie;
    }
}
