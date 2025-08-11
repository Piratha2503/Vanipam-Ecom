package com.service.Products.Service;

import com.service.Products.APIResponse.ApiPaginatedContentResponse;
import com.service.Products.DTO.RequestDTO.ProductSaveDTO;
import com.service.Products.DTO.RequestDTO.ProductUpdateDTO;
import com.service.Products.DTO.ResponseDTO.ProductResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponseDTO getById(Long id);

    List<ProductResponseDTO> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination);

    ProductResponseDTO create(@Valid ProductSaveDTO productRequest);

    ProductResponseDTO update(@Valid ProductUpdateDTO dto);

    void delete(Long id);
}
