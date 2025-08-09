package com.service.Users.DTO.RequestDTO;

import com.service.Users.Entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record BuyerSaveDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email(message = "Invalid email format") String email,
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number") String mobile,
        Address address,
        @NotBlank String userName,
        @NotBlank String password) {

}
