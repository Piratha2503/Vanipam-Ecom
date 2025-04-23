package com.service.Products.APIResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class APIContentResponse <T> extends ApiBaseResponses{

    private Map<String,T> contents = new HashMap<>();

    public APIContentResponse(String validationCode, String validationStatus, String validationMessage,
    String title,T content) {
        super(validationStatus, validationCode, validationMessage);
        contents.put(title,content);
    }
}
