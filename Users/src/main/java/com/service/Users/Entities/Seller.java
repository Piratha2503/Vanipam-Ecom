package com.service.Users.Entities;

import com.service.Users.Utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seller")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller extends DateTimeUtils {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    private String mobile;
    private String businessName;

    @Embedded
    private Address address;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
