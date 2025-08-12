package com.service.Products.Repositories;

import com.service.Products.Entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByProductNameAndBrandId(@NotBlank(message = "Product name is required") String s, @NotNull(message = "Brand ID is required") Long aLong);

    boolean existsByProductNameAndBrandIdAndIdNot(String s, Long aLong, @NotNull(message = "Product ID is required") Long id);

    @Query("SELECT p FROM Product p " +
            "JOIN FETCH p.productType " +
            "JOIN FETCH p.subCategory " +
            "JOIN FETCH p.brand ")
    Page<Product> findAllProducts(Pageable pageable);

    boolean existsByProductNameAndBrandIdAndSubCategoryId(@NotBlank(message = "Product name is required") String s, @NotNull(message = "Brand ID is required") Long aLong, @NotNull(message = "Subcategory ID is required") Long aLong1);

    boolean existsByProductNameAndBrandIdAndSubCategoryIdAndIdNot(String productName, Long brandId, Long subCategoryId, Long id);

    boolean existsByProductNameAndBrandIdAndProductTypeIdAndIdNot(String productName, Long brandId, Long productTypeId, Long id);

}
