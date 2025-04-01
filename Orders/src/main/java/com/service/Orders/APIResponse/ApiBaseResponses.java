package com.service.Orders.APIResponse;

import lombok.Data;

@Data

public class ApiBaseResponses {
    private String validation_status;
    private String validation_Code;
    private String validation_message;

    public ApiBaseResponses(String validation_Code, String validation_status, String validation_message){
        this.validation_Code = validation_Code;
        this.validation_status = validation_status;
        this.validation_message = validation_message;
    }
}
