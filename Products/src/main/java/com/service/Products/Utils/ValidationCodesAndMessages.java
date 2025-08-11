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

    // Main Category messages
    @Value("${message.success.save.mainCategory}")
    private String saveMainCategorySuccessMessage;
    @Value("${message.success.update.mainCategory}")
    private String updateMainCategorySuccessMessage;
    @Value("${message.success.get.mainCategory}")
    private String getMainCategorySuccessMessage;
    @Value("${message.success.delete.mainCategory}")
    private String deleteMainCategorySuccessMessage;
    @Value("${message.null.entity.mainCategory}")
    private String mainCategoryEntityNotFoundMessage;

    // Sub Category messages
    @Value("${message.success.save.subCategory}")
    private String saveSubCategorySuccessMessage;
    @Value("${message.success.update.subCategory}")
    private String updateSubCategorySuccessMessage;
    @Value("${message.success.get.subCategory}")
    private String getSubCategorySuccessMessage;
    @Value("${message.success.delete.subCategory}")
    private String deleteSubCategorySuccessMessage;
    @Value("${message.null.entity.subCategory}")
    private String subCategoryEntityNotFoundMessage;


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
