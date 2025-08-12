package com.service.Products.Repositories;

import com.service.Products.Entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {
}
