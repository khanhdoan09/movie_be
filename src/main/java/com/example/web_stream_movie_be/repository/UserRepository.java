package com.example.web_stream_movie_be.repository;

import com.example.web_stream_movie_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
//    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    User findUserById(Long id);

}
