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

    @GetMapping("/test")
    public String getIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr(); // Fallback to request IP
        }

        return "Client IP: " + ip;
    }

    @GetMapping(APIEndPoints.getMainCategoryList)
    public ResponseEntity<Object> getMainCategoryList(){

        List<MainCategoryResponse> mainCategoryList = mainCategoryService.getMainCategoryList();
        return ResponseEntity.ok().body(mainCategoryList);
    }

    @GetMapping(APIEndPoints.getMainCategoryByID)
    public ResponseEntity<Object> getMainCategoryByID(@PathVariable Long id){
        if (mainCategoryService.existMainCategoryById(id)) return ResponseEntity.ok().body("Id Not Exist");
        return ResponseEntity.ok().body(mainCategoryService.getMainCategoryById(id));
    }

    @PostMapping(APIEndPoints.saveMainCategory)
    public ResponseEntity<Object> saveMainCategory(@RequestBody MainCategoryRequest mainCategoryRequest){
        if (mainCategoryService.existMainCategoryByName(mainCategoryRequest.getMainCategoryName())) return ResponseEntity.ok().body("Exist By name");
        mainCategoryService.saveMainCategory(mainCategoryRequest);
        return ResponseEntity.ok().body("Saved");
    }

    @PutMapping(APIEndPoints.updateMainCategory)
    public ResponseEntity<Object> updateMainCategory(@RequestBody MainCategoryRequest mainCategoryRequest){
        if (mainCategoryService.existMainCategoryById(mainCategoryRequest.getId())) return ResponseEntity.ok().body("Id Not Exist");
        if (mainCategoryService.existMainCategoryByNameAndIdNot(mainCategoryRequest.getMainCategoryName(),mainCategoryRequest.getId()))
            return ResponseEntity.ok().body("Exist By name");
        mainCategoryService.updateMainCategory(mainCategoryRequest);
        return ResponseEntity.ok().body("Updated");
    }

    @DeleteMapping(APIEndPoints.deleteMainCategory)
    public ResponseEntity<Object> deleteMainCategory(@PathVariable Long id){
        if (mainCategoryService.existMainCategoryById(id)) return ResponseEntity.ok().body("Id Not Exist");
        mainCategoryService.deleteMainCategory(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
