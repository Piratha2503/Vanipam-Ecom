package com.service.Auth.ExceptionHandle.CustomExceptions;

public class DuplicateValuesException extends RuntimeException{

    public DuplicateValuesException(String message){
        super(message);
    }
}
