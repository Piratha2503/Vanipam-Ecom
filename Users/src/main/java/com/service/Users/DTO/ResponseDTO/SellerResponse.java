package com.service.Users.DTO.ResponseDTO;

import com.service.Users.Entities.Address;
import java.sql.Timestamp;

public record SellerResponse(Long id,
                             String firstName,
                             String lastName,
                             String email,
                             String mobile,
                             Address address,
                             String userName,
                             String businessName,
                             Timestamp created_timestamp,
                             Timestamp updated_timestamp) {}
