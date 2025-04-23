package com.service.Products.APIResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiBaseResponses {
    private String validationStatus;
    private String validationCode;
    private String validationMessage;

    public ApiBaseResponses(String validationCode, String validationStatus, String validationMessage){
        this.validationCode = validationCode;
        this.validationStatus = validationStatus;
        this.validationMessage = validationMessage;
    }
}
