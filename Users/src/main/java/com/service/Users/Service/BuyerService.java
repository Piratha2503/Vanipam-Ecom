package com.service.Users.Service;
import com.service.Users.APIResponse.ApiPaginatedContentResponse;
import com.service.Users.DTO.RequestDTO.BuyerSaveDTO;
import com.service.Users.DTO.RequestDTO.BuyerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BuyerService {

    BuyerResponse create(BuyerSaveDTO request);

    List<BuyerResponse> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination);

    BuyerResponse getById(Long id);

    BuyerResponse update(BuyerUpdateDTO dto);

    void delete(Long id);
}
