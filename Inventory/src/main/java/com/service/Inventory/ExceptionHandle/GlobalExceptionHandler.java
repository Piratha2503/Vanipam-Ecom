package com.service.Inventory.ExceptionHandle;


import com.service.Inventory.APIResponse.ApiBaseResponses;
import com.service.Inventory.Enums.ResponseStatus;
import com.service.Inventory.ExceptionHandle.CustomExceptions.DuplicateValuesException;
import com.service.Inventory.Utils.ValidationCodesAndMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.TransientPropertyValueException;
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
            String errorMessage = ((FieldError) error).getField();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ne){
        ne.printStackTrace();
        String code = validations.getCommonEntityNotFoundCode();
        String status = ResponseStatus.ERROR.getStatus();
        String msg = ne.getMessage();
        return ResponseEntity.ok(new ApiBaseResponses(code,status,msg));
    }

    @ExceptionHandler(DuplicateValuesException.class)
    public ResponseEntity<Object> handleDuplicateValuesException(DuplicateValuesException de){
        de.printStackTrace();
        String code = validations.getAlreadyExistCode();
        String status = ResponseStatus.DUPLICATE.getStatus();
        String msg = de.getMessage();
        return ResponseEntity.ok(new ApiBaseResponses(code,status,msg));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException enf){
        enf.printStackTrace();
        String code = validations.getCommonEntityNotFoundCode();
        String status = ResponseStatus.UNKNOWN.getStatus();
        String msg = enf.getMessage();
        return ResponseEntity.ok(new ApiBaseResponses(code,status,msg));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ie){
        ie.printStackTrace();

        String code = validations.getAlreadyExistCode();
        String status = ResponseStatus.ERROR.getStatus();
        String msg = ie.getMessage();

        return ResponseEntity.ok(new ApiBaseResponses(code,status,msg));
    }

    @ExceptionHandler(TransientPropertyValueException.class)
    public ResponseEntity<Object> handleTransientPropertyValueException(TransientPropertyValueException tve){
        tve.printStackTrace();

        String code = validations.getCommonFailureCode();
        String status = ResponseStatus.FAILURE.getStatus();
        String msg = tve.getMessage();

        return ResponseEntity.ok(new ApiBaseResponses(code,status,msg));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIllegalIOException(IOException ioe){
        ioe.printStackTrace();
        String code = validations.getAlreadyExistCode();
        String status = ResponseStatus.UNKNOWN.getStatus();
        String msg = ioe.getMessage();
        return ResponseEntity.ok(new ApiBaseResponses(code,status,msg));
    }
}
