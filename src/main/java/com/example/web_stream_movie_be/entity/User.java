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
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    @NotBlank(message = "password canot be blank")
    private String password;
    @Column(name = "is_admin")
    private boolean isAdmin;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

}
