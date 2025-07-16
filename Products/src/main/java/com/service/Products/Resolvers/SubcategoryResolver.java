package com.service.Products.Resolvers;

import com.service.Products.DTO.ResponseDTO.SubcategoryResponse;
import com.service.Products.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SubcategoryResolver {
    @Autowired
    private SubCategoryService subCategoryService;

    @QueryMapping
    public SubcategoryResponse getSubcategoryById(@Argument Long id){
        return subCategoryService.getSubCategoryById(id);
    }

    @QueryMapping
    public List<SubcategoryResponse> getSubcategoryList(){
        return subCategoryService.getSubCategoryList();
    }

}
