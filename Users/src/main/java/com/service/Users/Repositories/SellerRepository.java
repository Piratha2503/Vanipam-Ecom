package com.service.Users.Repositories;

import com.service.Users.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
}
