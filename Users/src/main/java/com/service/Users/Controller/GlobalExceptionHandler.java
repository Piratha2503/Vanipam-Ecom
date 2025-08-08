package com.service.Users.Controller;

import com.service.Users.APIResponse.ApiBaseResponses;
import com.service.Users.Enums.ResponseStatus;
import com.service.Users.Utils.ValidationCodesAndMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ValidationCodesAndMessages validations;

    private final String unknownStatus = ResponseStatus.UNKNOWN.getStatus();


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ne){
        ne.printStackTrace();
        return ResponseEntity.ok(new ApiBaseResponses(
                HttpStatus.NO_CONTENT.getReasonPhrase(),
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.hashCode()),
                ne.getMessage()
        ));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException enf){
        enf.printStackTrace();
        return ResponseEntity.ok(new ApiBaseResponses(
                validations.getCommonEntityNotFoundCode(),
                unknownStatus,
                enf.getMessage()
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ie){
        ie.printStackTrace();
        return ResponseEntity.ok(new ApiBaseResponses(
                "Illegal Argument Exception",
                "40000",
                ie.getLocalizedMessage()
        ));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIllegalIOException(IOException ioe){
        ioe.printStackTrace();
        return ResponseEntity.ok(new ApiBaseResponses(
                "IllegalIO Exception",
                "40000",
                ioe.getMessage()
        ));
    }
}
