package com.service.Products.Entities;

import com.service.Products.Utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
public class MainCategory extends DateTimeUtils {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mainCategory_name", nullable = false)
    private String mainCategoryName;

    @OneToMany(mappedBy = "main_category")
    private List<SubCategory> subCategories;


}
