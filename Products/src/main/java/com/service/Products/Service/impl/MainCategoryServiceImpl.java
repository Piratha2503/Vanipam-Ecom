package com.service.Products.Service.impl;

import com.service.Products.Repositories.MainCategoryRepository;
import com.service.Products.Service.MainCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

    @Autowired
    private MainCategoryRepository mainCategoryRepository;
}
