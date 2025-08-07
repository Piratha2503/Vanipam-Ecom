package com.service.Inventory.Utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:validation.properties")
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

    // Inventory messages
    @Value("${message.success.save.inventory}")
    private String saveInventorySuccessMessage;

    @Value("${message.success.update.inventory}")
    private String updateInventorySuccessMessage;

    @Value("${message.success.get.inventory}")
    private String getInventorySuccessMessage;

    @Value("${message.success.delete.inventory}")
    private String deleteInventorySuccessMessage;


}
