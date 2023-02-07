package com.example.web_stream_movie_be.repository;

import com.example.web_stream_movie_be.entity.MovieDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieDetailRepository extends JpaRepository<MovieDetail, Long> {

    @Query(value = "select * from movie_detail inner join movie on movie_detail.movie_id=movie.movie_id where slug = :slug", nativeQuery = true)
    Optional<MovieDetail> getAllById(String slug);
}