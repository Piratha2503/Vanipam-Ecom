package com.service.Products.ExceptionHandle.CustomExceptions;

public class DuplicateValuesException extends RuntimeException{

    public DuplicateValuesException(String message){
        super(message);
    }
}
