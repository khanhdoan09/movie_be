package com.example.web_stream_movie_be.entity.response;

import lombok.Data;

import java.util.List;

@Data
public class ListObjectResponse<T> {
    /*
    * ? :  represents an unknown type.
    *      The wildcard can be used in a variety of situations:
    *      as the type of a parameter, field, or local variable; sometimes as a return type
    * */
    private List<T> message;
}
