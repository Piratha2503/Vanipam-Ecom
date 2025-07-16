package com.service.Products.Controller;

import com.service.Products.DTO.RequestDTO.MainCategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;
import com.service.Products.Service.MainCategoryService;
import com.service.Products.Utils.APIEndPoints;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(APIEndPoints.baseAPI)
public class MainCategoryController {

    @Autowired
    private MainCategoryService mainCategoryService;

    @GetMapping(APIEndPoints.mainCategories)
    public ResponseEntity<Object> getMainCategoryList(){

        List<MainCategoryResponse> mainCategoryList = mainCategoryService.getMainCategoryList();
        return ResponseEntity.ok().body(mainCategoryList);
    }

    @GetMapping(APIEndPoints.mainCategoryById)
    public ResponseEntity<Object> getMainCategoryByID(@PathVariable Long id){
        if (!mainCategoryService.existMainCategoryById(id)) return ResponseEntity.ok().body("Id Not Exist");
        return ResponseEntity.ok().body(mainCategoryService.getMainCategoryById(id));
    }

    @PostMapping(APIEndPoints.mainCategory)
    public ResponseEntity<Object> saveMainCategory(@RequestBody MainCategoryRequest mainCategoryRequest){
        if (mainCategoryService.existMainCategoryByName(mainCategoryRequest.getMainCategoryName())) return ResponseEntity.ok().body("Exist By name");
        mainCategoryService.saveMainCategory(mainCategoryRequest);
        return ResponseEntity.ok().body("Saved");
    }

    @PutMapping(APIEndPoints.mainCategoryById)
    public ResponseEntity<Object> updateMainCategory(@RequestBody MainCategoryRequest mainCategoryRequest){
        if (mainCategoryService.existMainCategoryById(mainCategoryRequest.getId())) return ResponseEntity.ok().body("Id Not Exist");
        if (mainCategoryService.existMainCategoryByNameAndIdNot(mainCategoryRequest.getMainCategoryName(),mainCategoryRequest.getId()))
            return ResponseEntity.ok().body("Exist By name");
        mainCategoryService.updateMainCategory(mainCategoryRequest);
        return ResponseEntity.ok().body("Updated");
    }

    @DeleteMapping(APIEndPoints.mainCategoryById)
    public ResponseEntity<Object> deleteMainCategory(@PathVariable Long id){
        if (mainCategoryService.existMainCategoryById(id)) return ResponseEntity.ok().body("Id Not Exist");
        mainCategoryService.deleteMainCategory(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
