package com.service.Auth.Service;

import com.service.Users.APIResponse.ApiPaginatedContentResponse;
import com.service.Users.DTO.RequestDTO.SellerSaveDTO;
import com.service.Users.DTO.RequestDTO.SellerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.SellerResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SellerService {

    SellerResponse create(SellerSaveDTO dto);

    SellerResponse update(SellerUpdateDTO dto);

    SellerResponse getById(Long id);

    List<SellerResponse> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination);

    void delete(Long id);
}
