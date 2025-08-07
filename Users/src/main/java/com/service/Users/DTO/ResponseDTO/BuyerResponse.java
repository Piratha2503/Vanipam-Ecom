package com.service.Users.DTO.ResponseDTO;

import com.service.Users.Entities.Address;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class BuyerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private Address address;
    private String userName;
    private Timestamp created_timestamp;
    private Timestamp updated_timestamp;
}
