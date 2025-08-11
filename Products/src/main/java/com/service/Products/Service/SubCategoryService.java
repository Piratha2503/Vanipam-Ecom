package com.service.Products.Service;

import com.service.Products.APIResponse.ApiPaginatedContentResponse;
import com.service.Products.DTO.RequestDTO.SubCategoryRequest;
import com.service.Products.DTO.ResponseDTO.SubCategoryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubCategoryService {

    SubCategoryResponse getById(Long id);

    List<SubCategoryResponse> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination);

    SubCategoryResponse create(SubCategoryRequest request);

    SubCategoryResponse update(SubCategoryRequest request);

    void delete(Long id);
}
