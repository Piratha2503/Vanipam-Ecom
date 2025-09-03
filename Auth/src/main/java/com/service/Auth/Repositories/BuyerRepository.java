package com.service.Auth.Repositories;

import com.service.Users.Entities.Buyer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    boolean existsByEmailIgnoreCase(@Email(message = "Invalid email format") String email);

    boolean existsByUsernameIgnoreCase(@NotBlank String s);
}
