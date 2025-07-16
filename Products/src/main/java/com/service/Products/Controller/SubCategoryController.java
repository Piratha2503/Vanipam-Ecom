package com.service.Products.Controller;

import com.service.Products.DTO.RequestDTO.SubcategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;
import com.service.Products.Service.MainCategoryService;
import com.service.Products.Service.SubCategoryService;
import com.service.Products.Utils.APIEndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(APIEndPoints.baseAPI)
public class SubCategoryController {

    @Autowired
    private MainCategoryService mainCategoryService;
    @Autowired
    private SubCategoryService subCategoryService;


    @GetMapping(APIEndPoints.subCategories)
    public ResponseEntity<Object> getSubCategoryList(){
        return ResponseEntity.ok().body(subCategoryService.getSubCategoryList());
    }

    @GetMapping(APIEndPoints.subCategoryByID)
    public ResponseEntity<Object> getSubCategoryByID(@PathVariable Long id){
        if (subCategoryService.existSubCategoryById(id)) return ResponseEntity.ok().body("Subcategory Id does not Exist");
        return ResponseEntity.ok().body(subCategoryService.getSubCategoryById(id));
    }

    @PostMapping(APIEndPoints.subCategory)
    public ResponseEntity<Object> saveSubCategory(@RequestBody SubcategoryRequest subcategoryRequest){
        if (!mainCategoryService.existMainCategoryById(subcategoryRequest.getMainCategory_id())) return ResponseEntity.ok().body("MainCategory Id does not Exist");
        if (subCategoryService.existSubCategoryByName(subcategoryRequest.getSubCategoryName())) return ResponseEntity.ok().body("Exist By name");
        subCategoryService.saveSubCategory(subcategoryRequest);
        return ResponseEntity.ok().body(subcategoryRequest);
    }

    @PutMapping(APIEndPoints.subCategoryByID)
    public ResponseEntity<Object> updateSubCategory(
            @PathVariable("id") Long id,
            @RequestBody SubcategoryRequest subcategoryRequest){
        if (id == null) return ResponseEntity.ok().body("Subcategory id cannot be null or empty");
        if (!mainCategoryService.existMainCategoryById(subcategoryRequest.getMainCategory_id())) return ResponseEntity.ok().body("MainCategory Id does not Exist");
        if (!subCategoryService.existSubCategoryById(id)) return ResponseEntity.ok().body("SubCategory Id does not Exist");
        if (subCategoryService.existSubCategoryByNameAndIdNot(subcategoryRequest.getSubCategoryName(),subcategoryRequest.getId()))
            return ResponseEntity.ok().body("Exist By name");
        subcategoryRequest.setId(id);
        subCategoryService.updateSubCategory(subcategoryRequest);
        return ResponseEntity.ok().body("Updated");
    }

    @DeleteMapping(APIEndPoints.subCategoryByID)
    public ResponseEntity<Object> deleteSubCategory(@PathVariable Long id){
        if (subCategoryService.existSubCategoryById(id)) return ResponseEntity.ok().body("Id Not Exist");
        subCategoryService.deleteSubCategory(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
