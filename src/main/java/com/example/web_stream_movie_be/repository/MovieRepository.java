package com.example.web_stream_movie_be.repository;

import com.example.web_stream_movie_be.model.Movie;
import com.example.web_stream_movie_be.model.temporary.Temporary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query(value = "select * from movie where name like %:movieName%", nativeQuery = true)
    List<Movie> findByName(String movieName);
    @Query(value = "select count(movie_id) from movie where name like %:movieName%", nativeQuery = true)
    int countByName(String movieName);

}