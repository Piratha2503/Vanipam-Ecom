package com.service.Products.Logging;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:logs.properties")
@Data
public class ProductLogs {

    @Value("${log.creating.product}")
    private String creatingProductLog;

    @Value("${log.created.product}")
    private String createdProductLog;

    @Value("${log.updating.product}")
    private String updatingProductLog;

    @Value("${log.updated.product}")
    private String updatedProductLog;

    @Value("${log.deleting.product}")
    private String deletingProductLog;

    @Value("${log.deleted.product}")
    private String deletedProductLog;

    @Value("${log.fetching.product}")
    private String fetchingProductLog;

    @Value("${log.fetched.product}")
    private String fetchedProductLog;

    @Value("${log.fetching.products}")
    private String fetchingProductsLog;

    @Value("${log.fetched.products}")
    private String fetchedProductsLog;
}
