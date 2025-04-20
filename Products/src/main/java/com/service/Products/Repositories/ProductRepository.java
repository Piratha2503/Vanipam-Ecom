package com.service.Products.Repositories;

import com.service.Products.Entities.Product;
import com.service.Products.Entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
