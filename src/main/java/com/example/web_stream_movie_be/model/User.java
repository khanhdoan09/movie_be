package com.example.web_stream_movie_be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String username;
    private String password;
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Column(name = "full_name")
    private String fullName;

}
