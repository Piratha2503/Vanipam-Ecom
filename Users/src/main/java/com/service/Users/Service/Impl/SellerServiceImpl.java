package com.service.Users.Service.Impl;

import com.service.Users.APIResponse.ApiPaginatedContentResponse;
import com.service.Users.DTO.RequestDTO.SellerSaveDTO;
import com.service.Users.DTO.RequestDTO.SellerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.SellerResponse;
import com.service.Users.Entities.Address;
import com.service.Users.Entities.Seller;
import com.service.Users.ExceptionHandle.CustomExceptions.DuplicateValuesException;
import com.service.Users.Repositories.SellerRepository;
import com.service.Users.Service.SellerService;
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
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ValidationCodesAndMessages validationMessages;

    @Override
    public SellerResponse create(SellerSaveDTO dto) {
        if (sellerRepository.existsByEmailIgnoreCase(dto.email()))
            throw new DuplicateValuesException(validationMessages.getEmailAlreadyExistMessage());

        if (sellerRepository.existsByMobileIgnoreCase(dto.mobile()))
            throw new DuplicateValuesException(validationMessages.getMobileAlreadyExistMessage());

        if (sellerRepository.existsByUsernameIgnoreCase(dto.userName()))
            throw new DuplicateValuesException(validationMessages.getUsernameAlreadyExistMessage());


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

        if (sellerRepository.existsByEmailIgnoreCaseAndIdNot(dto.email(), dto.id()))
            throw new DuplicateValuesException(validationMessages.getEmailAlreadyExistMessage());

        if (sellerRepository.existsByMobileIgnoreCaseAndIdNot(dto.mobile(), dto.id()))
            throw new DuplicateValuesException(validationMessages.getMobileAlreadyExistMessage());

        if (sellerRepository.existsByUsernameIgnoreCaseAndIdNot(dto.userName(),dto.id()))
            throw new DuplicateValuesException(validationMessages.getUsernameAlreadyExistMessage());

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
    public List<SellerResponse> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination) {

        Page<Seller> buyers = sellerRepository.findAll(pageable);
        pagination.setTotalPages(buyers.getTotalPages());
        pagination.setTotalRecords(buyers.getTotalElements());

        return buyers.get().map(this::sellerToSellerResponse).toList();
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
