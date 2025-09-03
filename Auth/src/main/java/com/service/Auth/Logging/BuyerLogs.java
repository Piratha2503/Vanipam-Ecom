package com.service.Auth.Logging;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:logs.properties")
@Data
public class BuyerLogs {
    @Value("${log.creating.buyer}")
    private String creatingBuyerLog;
    @Value("${log.created.buyer}")
    private String createdBuyerLog;
    @Value("${log.updating.buyer}")
    private String updatingBuyerLog;
    @Value("${log.updated.buyer}")
    private String updatedBuyerLog;
    @Value("${log.deleting.buyer}")
    private String deletingBuyerLog;
    @Value("${log.deleted.buyer}")
    private String deletedBuyerLog;
    @Value("${log.fetching.buyer}")
    private String fetchingBuyerLog;
    @Value("${log.fetched.buyer}")
    private String fetchedBuyerLog;
    @Value("${log.fetching.buyers}")
    private String fetchingBuyersLog;
    @Value("${log.fetched.buyers}")
    private String fetchedBuyersLog;
}
