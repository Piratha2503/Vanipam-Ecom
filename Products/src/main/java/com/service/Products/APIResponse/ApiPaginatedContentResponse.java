package com.service.Products.APIResponse;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ApiPaginatedContentResponse<T> extends APIContentResponse<T>{

    private Map<String,T> contents = new HashMap<>();

    public ApiPaginatedContentResponse(String validation_Code, String validation_status,  String validation_message, String title, T content) {
        super(validation_Code, validation_status, validation_message, title, content);
    }

    private Pagination pagination;

    public ApiPaginatedContentResponse(String validation_Code, String validation_status,  String validation_message, String title, T content, Pagination pagination){
        super(validation_Code, validation_status, validation_message, title, content);
        contents.put(title,content);
        this.pagination = pagination;
    }


    @Data
    @Builder
    @AllArgsConstructor
    public static class Pagination{
        private Integer pageNumber;
        private Integer pageSize;
        private Integer totalPages;
        private Long totalRecords;
    }

}
