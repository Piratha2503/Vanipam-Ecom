package com.service.Users.Logging;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:logs.properties")
@Data
public class SellerLogs {
    @Value("${log.creating.seller}")
    private String creatingSellerLog;
    @Value("${log.created.seller}")
    private String createdSellerLog;
    @Value("${log.updating.seller}")
    private String updatingSellerLog;
    @Value("${log.updated.seller}")
    private String updatedSellerLog;
    @Value("${log.deleting.seller}")
    private String deletingSellerLog;
    @Value("${log.deleted.seller}")
    private String deletedSellerLog;
    @Value("${log.fetching.seller}")
    private String fetchingSellerLog;
    @Value("${log.fetched.seller}")
    private String fetchedSellerLog;
    @Value("${log.fetching.sellers}")
    private String fetchingSellersLog;
    @Value("${log.fetched.sellers}")
    private String fetchedSellersLog;
}
