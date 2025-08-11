package com.service.Users.Controlllers;

import com.service.Users.DTO.RequestDTO.BuyerSaveDTO;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;
import com.service.Users.Entities.Address;
import com.service.Users.Entities.Buyer;
import com.service.Users.Repositories.BuyerRepository;
import com.service.Users.Service.Impl.BuyerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuyerServiceImplTest {

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private BuyerServiceImpl buyerService;

    private BuyerSaveDTO buildBuyerSaveDTO() {

        Address address = new Address();
        address.setHouseNo(123L);
        address.setStreetName("Main St");
        address.setCity("Springfield");
        address.setDistrict("Central");
        address.setState("IL");
        address.setCountry("USA");
        address.setCountryCode("US");
        address.setPostalCode(62704L);

        return new BuyerSaveDTO(
                "John",
                "Doe",
                "john.doe@example.com",
                "+12345678901",
                address,
                "johndoe",
                "SecurePass123"
        );
    }

    @Test
    void create_shouldThrowWhenEmailExists() {
        BuyerSaveDTO dto = buildBuyerSaveDTO();

        when(buyerRepository.existsByEmailIgnoreCase(dto.email())).thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> buyerService.create(dto));

        verify(buyerRepository).existsByEmailIgnoreCase(dto.email());
        verify(buyerRepository, never()).save(any(Buyer.class));
    }

    @Test
    void create_shouldThrowWhenUsernameExists() {
        BuyerSaveDTO dto = buildBuyerSaveDTO();

        when(buyerRepository.existsByEmailIgnoreCase(dto.email())).thenReturn(false);
        when(buyerRepository.existsByUsernameIgnoreCase(dto.userName())).thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> buyerService.create(dto));

        verify(buyerRepository).existsByUsernameIgnoreCase(dto.userName());
        verify(buyerRepository, never()).save(any(Buyer.class));
    }

    @Test
    void create_shouldSaveBuyerWhenNoConflicts() {
        BuyerSaveDTO dto = buildBuyerSaveDTO();

        when(buyerRepository.existsByEmailIgnoreCase(dto.email())).thenReturn(false);
        when(buyerRepository.existsByUsernameIgnoreCase(dto.userName())).thenReturn(false);
        when(passwordEncoder.encode(dto.password())).thenReturn("encodedPass");

        Buyer savedBuyer = Buyer.builder()
                .firstname(dto.firstName())
                .lastname(dto.lastName())
                .email(dto.email())
                .mobile(dto.mobile())
                .address(dto.address())
                .username(dto.userName())
                .password("encodedPass")
                .build();

        when(buyerRepository.save(any(Buyer.class))).thenReturn(savedBuyer);

        BuyerResponse response = buyerService.create(dto);

        Assertions.assertEquals(dto.email(), response.email());
        Assertions.assertEquals(dto.userName(), response.username());
        verify(buyerRepository).save(any(Buyer.class));
    }
}
