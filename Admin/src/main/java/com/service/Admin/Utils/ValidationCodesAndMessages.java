package com.service.Admin.Utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ValidationCodes&Messages.properties")
@Data
public class ValidationCodesAndMessages {

    @Value("${code.success.common}")
    private String commonSuccessCode;
    @Value("${code.failure.common}")
    private String commonFailureCode;
    @Value("${code.nullValues.received}")
    private String NullValuesCode;
    @Value("${code.failure.file}")
    private String fileFailureCode;

    //product messages
    @Value("${message.success.save}")
    private String saveSuccessMessage;
    @Value("${message.success.update}")
    private String updateSuccessMessage;
    @Value("${message.success.get}")
    private String getSuccessMessage;
    @Value("${message.success.delete}")
    private String deleteSuccessMessage;


}
