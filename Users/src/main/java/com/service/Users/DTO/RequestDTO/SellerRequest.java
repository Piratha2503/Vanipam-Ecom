package com.service.Users.DTO.RequestDTO;

import lombok.Data;

@Data
public class SellerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String userName;
    private String password;
}
