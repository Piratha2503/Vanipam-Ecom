package com.service.Users.DTO.RequestDTO;

import com.service.Users.Entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BuyerRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number")
    private String mobile;

    @NotBlank
    private Address address;

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
