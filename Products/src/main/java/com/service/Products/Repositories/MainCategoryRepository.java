package com.service.Products.Repositories;

import com.service.Products.Entities.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory,Long> {

       boolean existsByMainCategoryNameIgnoreCase(String mainCategoryName);

       boolean existsByMainCategoryNameIgnoreCaseAndIdNot(String mainCategoryName, Long id);
}
