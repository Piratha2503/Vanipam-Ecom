package com.service.Users.Utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:logs.properties")
@Data
public class LogMessages {
    @Value("${log.create.seller}")
    private String createSellerLog;
    @Value("${log.update.seller}")
    private String updateSellerLog;
    @Value("${log.delete.seller}")
    private String deleteSellerLog;
    @Value("${log.fetch.seller}")
    private String fetchSellerLog;
    @Value("${log.fetch.sellers}")
    private String fetchSellersLog;
}
