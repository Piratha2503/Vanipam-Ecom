package com.service.Users.Service.Impl;

import com.service.Users.DTO.RequestDTO.BuyerRequest;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;
import com.service.Users.Entities.Buyer;
import com.service.Users.Repositories.BuyerRepository;
import com.service.Users.Service.BuyerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;

    @Override
    public BuyerResponse createBuyer(BuyerRequest request) {
        Buyer buyer = mapToEntity(request);
        buyer = buyerRepository.save(buyer);
        return mapToResponse(buyer);
    }

    @Override
    public List<BuyerResponse> getAllBuyers() {
        return buyerRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public BuyerResponse getBuyerById(Long id) {
        Buyer buyer = buyerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Buyer not found with id: " + id));
        return mapToResponse(buyer);
    }

    @Override
    public BuyerResponse updateBuyer(Long id, BuyerRequest request) {
        Buyer buyer = buyerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Buyer not found with id: " + id));
        updateEntity(buyer, request);
        buyer = buyerRepository.save(buyer);
        return mapToResponse(buyer);
    }

    @Override
    public void deleteBuyer(Long id) {
        buyerRepository.deleteById(id);
    }

    private Buyer mapToEntity(BuyerRequest request) {
        Buyer buyer = new Buyer();
        updateEntity(buyer, request);
        return buyer;
    }

    private void updateEntity(Buyer buyer, BuyerRequest request) {
        buyer.setFirstName(request.firstName());
        buyer.setLastName(request.lastName());
        buyer.setEmail(request.email());
        buyer.setMobile(request.mobile());
        buyer.setAddress(request.address());
        buyer.setUserName(request.userName());
        buyer.setPassword(request.password());
    }

    private BuyerResponse mapToResponse(Buyer buyer) {
        return BuyerResponse.builder()
                .id(buyer.getId())
                .firstName(buyer.getFirstName())
                .lastName(buyer.getLastName())
                .email(buyer.getEmail())
                .mobile(buyer.getMobile())
                .address(buyer.getAddress())
                .userName(buyer.getUserName())
                .created_timestamp(buyer.getCreated_timestamp())
                .updated_timestamp(buyer.getUpdated_timestamp())
                .build();
    }
}
