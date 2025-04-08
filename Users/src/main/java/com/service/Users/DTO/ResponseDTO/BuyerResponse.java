package com.service.Users.DTO.ResponseDTO;

import lombok.Data;

@Data
public class BuyerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String userName;
    private String password;
}
