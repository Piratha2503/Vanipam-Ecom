package com.service.Users.Repositories;

import com.service.Users.Entities.Seller;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {

    boolean existsByUsernameIgnoreCase(@NotBlank String s);

    boolean existsByEmailIgnoreCase(@Email(message = "Invalid email format") String email);

    boolean existsByMobileIgnoreCase(@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number") String mobile);

    boolean existsByEmailIgnoreCaseAndIdNot(@Email(message = "Invalid email format") String email,Long id);

    boolean existsByMobileIgnoreCaseAndIdNot(@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number format") String mobile,Long id);

    boolean existsByUsernameIgnoreCaseAndIdNot(String s,Long id);
}
