package com.example.web_stream_movie_be.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "username cannot be null")
    @NotEmpty(message = "username cannot be empty")
    @NotBlank(message = "username canot be blank")
    private String username;
    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    @NotBlank(message = "password canot be blank")
    private String password;
    @Column(name = "is_admin")
    private boolean isAdmin;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
