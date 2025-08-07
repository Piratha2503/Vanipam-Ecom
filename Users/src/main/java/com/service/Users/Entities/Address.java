package com.service.Users.Entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private Long houseNo;
    private String streetName;
    private String city;
    private String district;
    private String state;
    private String country;
    private String countryCode;
    private Long postalCode;
}
