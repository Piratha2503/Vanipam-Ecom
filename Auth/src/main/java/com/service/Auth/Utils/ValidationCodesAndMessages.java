package com.service.Auth.Utils;

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



    //common message
    @Value("${message.already.exist.email}")
    private String emailAlreadyExistMessage;
    @Value("${message.already.exist.mobile}")
    private String mobileAlreadyExistMessage;
    @Value("${message.already.exist.username}")
    private String usernameAlreadyExistMessage;

    //Buyer messages
    @Value("${message.success.save.buyer}")
    private String saveBuyerSuccessMessage;
    @Value("${message.success.update.buyer}")
    private String updateBuyerSuccessMessage;
    @Value("${message.success.get.buyer}")
    private String getBuyerSuccessMessage;
    @Value("${message.success.delete.buyer}")
    private String deleteBuyerSuccessMessage;
    @Value("${message.null.entity.buyer}")
    private String buyerEntityNotFoundMessage;

    //Seller messages
    @Value("${message.success.save.seller}")
    private String saveSellerSuccessMessage;
    @Value("${message.success.update.seller}")
    private String updateSellerSuccessMessage;
    @Value("${message.success.get.seller}")
    private String getSellerSuccessMessage;
    @Value("${message.success.delete.seller}")
    private String deleteSellerSuccessMessage;
    @Value("${message.null.entity.seller}")
    private String sellerEntityNotFoundMessage;


}
