package com.service.Users.Repositories;

import com.service.Users.Entities.Seller;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {

    boolean existsByUsernameIgnoreCase(@NotBlank String s);

    boolean existsByEmailIgnoreCase(@Email(message = "Invalid email format") String email);
}
