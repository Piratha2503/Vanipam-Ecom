package com.service.Products.Controller;

import com.service.Products.APIResponse.ApiBaseResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ne){

        return ResponseEntity.ok(new ApiBaseResponses(
                "Validation Failed",
                "40000",
                ne.getMessage()
        ));

        }
}
