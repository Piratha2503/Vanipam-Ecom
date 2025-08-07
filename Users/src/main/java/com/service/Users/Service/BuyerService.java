package com.service.Users.Service;
import com.service.Users.DTO.RequestDTO.BuyerRequest;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;

import java.util.List;


public interface BuyerService {

    BuyerResponse createBuyer(BuyerRequest request);

    List<BuyerResponse> getAllBuyers();

    BuyerResponse getBuyerById(Long id);

    BuyerResponse updateBuyer(Long id, BuyerRequest request);

    void deleteBuyer(Long id);
}
