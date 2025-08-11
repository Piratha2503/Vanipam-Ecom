package com.service.Products.Repositories;

import com.service.Products.Entities.SubCategory;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    boolean existsBySubCategoryNameIgnoreCase(@NotNull String s);
}
