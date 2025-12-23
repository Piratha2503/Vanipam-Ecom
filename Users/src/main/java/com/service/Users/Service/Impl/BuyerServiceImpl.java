package com.service.Users.Service.Impl;

import com.service.Users.APIResponse.ApiPaginatedContentResponse;
import com.service.Users.DTO.RequestDTO.BuyerSaveDTO;
import com.service.Users.DTO.RequestDTO.BuyerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;
import com.service.Users.Entities.Address;
import com.service.Users.Entities.Buyer;
import com.service.Users.Repositories.BuyerRepository;
import com.service.Users.Service.BuyerService;
import com.service.Users.Utils.ValidationCodesAndMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ValidationCodesAndMessages validationMessages;

    @Override
    public BuyerResponse create(BuyerSaveDTO dto) {
        if (buyerRepository.existsByEmailIgnoreCase(dto.email())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (buyerRepository.existsByUsernameIgnoreCase(dto.userName())) {
            throw new IllegalArgumentException("Username already in use");
        }

        return buyerToBuyerResponse(buyerRepository.save(Buyer.builder()
                .firstname(dto.firstName())
                .lastname(dto.lastName())
                .email(dto.email())
                .mobile(dto.mobile())
                .address(dto.address())
                .username(dto.userName())
                .password(passwordEncoder.encode(dto.password()))
                .build()));
    }

    @Override
    public BuyerResponse update(BuyerUpdateDTO dto) {
        Buyer existing = buyerRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException(validationMessages.getBuyerEntityNotFoundMessage()));

        String firstName = dto.firstName() != null ? dto.firstName() : existing.getFirstname();
        String lastName = dto.lastName() != null ? dto.lastName() : existing.getLastname();
        String email = dto.email() != null ? dto.email() : existing.getEmail();
        String mobile = dto.mobile() != null ? dto.mobile() : existing.getMobile();
        Address address = dto.address() != null ? dto.address() : existing.getAddress();
        String userName = dto.userName() != null ? dto.userName() : existing.getUsername();

        Buyer buyer = Buyer.builder()
                .id(existing.getId())
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .mobile(mobile)
                .address(address)
                .username(userName)
                .build();

        if (dto.password() != null) {
            buyer.setPassword(passwordEncoder.encode(dto.password()));
        }

        return buyerToBuyerResponse(buyerRepository.save(buyer));
    }

    @Override
    public BuyerResponse getById(Long id) {
        return buyerToBuyerResponse(
                buyerRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(validationMessages.getBuyerEntityNotFoundMessage()))
        );
    }

    @Override
    public List<BuyerResponse> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination) {
        Page<Buyer> buyers = buyerRepository.findAll(pageable);
        pagination.setTotalPages(buyers.getTotalPages());
        pagination.setTotalRecords(buyers.getTotalElements());
        return buyers.get().map(this::buyerToBuyerResponse).toList();
    }

    @Override
    public void delete(Long id) {
        Buyer existing = buyerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(validationMessages.getBuyerEntityNotFoundMessage()));

        buyerRepository.delete(existing);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return buyerRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return null;
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return null;
    }

    BuyerResponse buyerToBuyerResponse(Buyer buyer) {
        return new BuyerResponse(
                buyer.getId(),
                buyer.getFirstname(),
                buyer.getLastname(),
                buyer.getEmail(),
                buyer.getMobile(),
                buyer.getAddress(),
                buyer.getUsername(),
                buyer.getCreated_timestamp(),
                buyer.getUpdated_timestamp()
        );
    }
}
