package com.example.web_stream_movie_be.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Column(name = "full_name")
    private String fullName;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
