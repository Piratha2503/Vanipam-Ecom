package com.service.Products.Controller;

import com.service.Products.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;
}
