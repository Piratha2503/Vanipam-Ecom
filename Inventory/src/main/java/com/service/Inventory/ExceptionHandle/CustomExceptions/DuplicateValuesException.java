package com.service.Inventory.ExceptionHandle.CustomExceptions;

public class DuplicateValuesException extends RuntimeException{

    public DuplicateValuesException(String message){
        super(message);
    }
}
