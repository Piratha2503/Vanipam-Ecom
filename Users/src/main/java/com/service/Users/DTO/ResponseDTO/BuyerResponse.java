package com.service.Users.DTO.ResponseDTO;

import com.service.Users.Entities.Address;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record BuyerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String mobile,
        Address address,
        String username,
        Timestamp created_timestamp,
        Timestamp updated_timestamp) {

}
