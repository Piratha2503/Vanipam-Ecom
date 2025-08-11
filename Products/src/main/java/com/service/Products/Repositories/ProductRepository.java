package com.service.Products.Repositories;

import com.service.Products.Entities.Product;
import com.service.Products.Entities.SubCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByProductNameAndBrandId(@NotBlank(message = "Product name is required") String s, @NotNull(message = "Brand ID is required") Long aLong);

    boolean existsByProductNameAndBrandIdAndIdNot(String s, Long aLong, @NotNull(message = "Product ID is required") Long id);
}
