package com.service.Users.Entities;

import com.service.Users.Utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Buyer extends DateTimeUtils {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    @Embedded
    private Address address;
    private String userName;
    private String password;
}
