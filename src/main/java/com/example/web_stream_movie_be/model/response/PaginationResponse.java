package com.example.web_stream_movie_be.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationResponse {
    private int currentPage;
    private int totalItems;
    private int totalItemsPerPage;
}
