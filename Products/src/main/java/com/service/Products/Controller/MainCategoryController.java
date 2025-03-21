package com.service.Products.Controller;

import com.service.Products.Service.MainCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MainCategoryController {
    @Autowired
    private MainCategoryService mainCategoryService;

}
