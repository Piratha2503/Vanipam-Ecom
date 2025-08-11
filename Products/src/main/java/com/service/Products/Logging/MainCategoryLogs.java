package com.service.Products.Logging;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:logs.properties")
@Data
public class MainCategoryLogs {

    @Value("${log.creating.maincategory}")
    private String creatingMainCategoryLog;

    @Value("${log.created.maincategory}")
    private String createdMainCategoryLog;

    @Value("${log.updating.maincategory}")
    private String updatingMainCategoryLog;

    @Value("${log.updated.maincategory}")
    private String updatedMainCategoryLog;

    @Value("${log.deleting.maincategory}")
    private String deletingMainCategoryLog;

    @Value("${log.deleted.maincategory}")
    private String deletedMainCategoryLog;

    @Value("${log.fetching.maincategory}")
    private String fetchingMainCategoryLog;

    @Value("${log.fetched.maincategory}")
    private String fetchedMainCategoryLog;

    @Value("${log.fetching.maincategories}")
    private String fetchingMainCategoriesLog;

    @Value("${log.fetched.maincategories}")
    private String fetchedMainCategoriesLog;
}

