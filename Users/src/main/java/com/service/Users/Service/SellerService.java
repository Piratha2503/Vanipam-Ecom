package com.service.Users.Service;

import com.service.Users.DTO.RequestDTO.SellerSaveDTO;
import com.service.Users.DTO.RequestDTO.SellerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.SellerResponse;

import java.util.List;

public interface SellerService {
    SellerResponse create(SellerSaveDTO dto);

    SellerResponse update(SellerUpdateDTO dto);

    SellerResponse getById(Long id);

    List<SellerResponse> getAll();

    void delete(Long id);
}
