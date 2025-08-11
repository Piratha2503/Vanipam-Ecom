package com.service.Products.Utils;

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
    @Value("${code.null.entity.common}")
    private String commonEntityNotFoundCode;
    @Value("${code.nullValues.received}")
    private String NullValuesCode;
    @Value("${code.failure.file}")
    private String fileFailureCode;
    @Value("${code.already.exist}")
    private String alreadyExistCode;

    // Product messages
    @Value("${message.success.save.product}")
    private String saveProductSuccessMessage;
    @Value("${message.success.update.product}")
    private String updateProductSuccessMessage;
    @Value("${message.success.get.product}")
    private String getProductSuccessMessage;
    @Value("${message.success.delete.product}")
    private String deleteProductSuccessMessage;
    @Value("${message.null.entity.product}")
    private String productEntityNotFoundMessage;


}
