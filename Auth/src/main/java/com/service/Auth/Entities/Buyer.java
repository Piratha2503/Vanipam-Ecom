package com.service.Auth.Entities;

import com.service.Users.Utils.DateTimeUtils;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Buyer extends DateTimeUtils {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String mobile;

    @Embedded
    private Address address;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
}
