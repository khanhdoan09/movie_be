package com.example.web_stream_movie_be.repository;

import com.example.web_stream_movie_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
//    User findByUsernameAndPassword(String username, String password);
    User findByEmail(String email);
    User findUserById(Long id);
}
