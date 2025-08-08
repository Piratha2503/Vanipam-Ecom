package com.service.Users.DTO.RequestDTO;

import com.service.Users.Entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SellerUpdateDTO(
        @NotNull Long id,
        String firstName,
        String lastName,
        @Email(message = "Invalid email format") String email,
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number format") String mobile,
        String businessName,
        Address address,
        String userName,
        String password) {}
