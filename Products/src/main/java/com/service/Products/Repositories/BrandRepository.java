package com.service.Products.Repositories;

import com.service.Products.Entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Long> {

    Optional<Brand> findByBrandName(String id);
}
