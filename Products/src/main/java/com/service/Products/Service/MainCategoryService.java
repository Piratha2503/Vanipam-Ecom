package com.service.Products.Service;

import com.service.Products.APIResponse.ApiPaginatedContentResponse;
import com.service.Products.DTO.RequestDTO.MainCategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MainCategoryService {

    MainCategoryResponse create(MainCategoryRequest dto);

    MainCategoryResponse update(MainCategoryRequest dto);

    MainCategoryResponse getById(Long id);

    List<MainCategoryResponse> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination);

    void delete(Long id);
}
