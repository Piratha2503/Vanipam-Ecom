package com.service.Users.Repositories;

import com.service.Users.Entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer,Long> {
}
