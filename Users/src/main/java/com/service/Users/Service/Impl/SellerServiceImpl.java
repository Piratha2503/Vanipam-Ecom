package com.service.Users.Service.Impl;

import com.service.Users.DTO.RequestDTO.SellerSaveDTO;
import com.service.Users.DTO.RequestDTO.SellerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.SellerResponse;
import com.service.Users.Entities.Address;
import com.service.Users.Entities.Seller;
import com.service.Users.Repositories.SellerRepository;
import com.service.Users.Service.SellerService;
import com.service.Users.Utils.ValidationCodesAndMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ValidationCodesAndMessages validationMessages;


    @Override
    public SellerResponse create(SellerSaveDTO dto) {
        if (sellerRepository.existsByEmailIgnoreCase(dto.email())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (sellerRepository.existsByUsernameIgnoreCase(dto.userName())) {
            throw new IllegalArgumentException("Username already in use");
        }

        return sellerToSellerResponse(sellerRepository.save(Seller.builder()
                .firstname(dto.firstName())
                .lastname(dto.lastName())
                .email(dto.email())
                .address(dto.address())
                .mobile(dto.mobile())
                .businessName(dto.businessName())
                .username(dto.userName())
                .password(passwordEncoder.encode(dto.password()))
                .build()));
    }

    @Override
    public SellerResponse update(SellerUpdateDTO dto) {
        Seller existing = sellerRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException(validationMessages.getSellerEntityNotFoundMessage()));

        String firstname = dto.firstName() != null ? dto.firstName() : existing.getFirstname();
        String lastname = dto.lastName() != null ? dto.lastName() : existing.getLastname();
        String email = dto.email() != null ? dto.email() : existing.getEmail();
        String mobile = dto.mobile() != null ? dto.mobile() : existing.getMobile();
        String businessName = dto.businessName() != null ? dto.businessName() : existing.getBusinessName();
        Address address = dto.address() != null ? dto.address() : existing.getAddress();
        String username = dto.userName() != null ? dto.userName() : existing.getUsername();

        Seller seller = Seller.builder()
                .id(existing.getId())
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .mobile(mobile)
                .businessName(businessName)
                .address(address)
                .username(username)
                .build();

        if (dto.password() != null) seller.setPassword(passwordEncoder.encode(dto.password()));

        return sellerToSellerResponse(sellerRepository.save(seller));
    }

    @Override
    public SellerResponse getById(Long id) {
        return sellerToSellerResponse(sellerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(validationMessages.getSellerEntityNotFoundMessage())));
    }

    @Override
    public List<SellerResponse> getAll() {
        return sellerRepository.findAll().stream().map(this::sellerToSellerResponse).toList();
    }

    @Override
    public void delete(Long id) {
        Seller existing = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(validationMessages.getSellerEntityNotFoundMessage()));

        sellerRepository.delete(existing);
    }

    SellerResponse sellerToSellerResponse(Seller seller){
        return new SellerResponse(
                seller.getId(),
                seller.getFirstname(),
                seller.getLastname(),
                seller.getEmail(),
                seller.getMobile(),
                seller.getAddress(),
                seller.getUsername(),
                seller.getBusinessName(),
                seller.getCreated_timestamp(),
                seller.getUpdated_timestamp());
    }

}
