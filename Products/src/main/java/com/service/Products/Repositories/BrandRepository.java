package com.service.Products.Repositories;

import com.service.Products.Entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {

}
