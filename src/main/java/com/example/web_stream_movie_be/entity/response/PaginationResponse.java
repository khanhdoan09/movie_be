package com.example.web_stream_movie_be.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse {
    private int currentPage;
    private int totalItems;
    private int totalItemsPerPage;
}
