package com.example.web_stream_movie_be.repository;

import com.example.web_stream_movie_be.entity.MovieDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDetailRepository extends JpaRepository<MovieDetail, Long> {

    @Query(value = "select * from movie_detail where id= :movieId", nativeQuery = true)
    MovieDetail getBriefMovieDetail(String movieId);
    @Query(value = "select * from movie_detail where id= :movieId", nativeQuery = true)
    MovieDetail getAllById(String movieId);
}