package com.service.Products.Logging;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:logs.properties")
@Data
public class SubCategoryLogs {

    @Value("${log.creating.subcategory}")
    private String creatingSubCategoryLog;

    @Value("${log.created.subcategory}")
    private String createdSubCategoryLog;

    @Value("${log.updating.subcategory}")
    private String updatingSubCategoryLog;

    @Value("${log.updated.subcategory}")
    private String updatedSubCategoryLog;

    @Value("${log.deleting.subcategory}")
    private String deletingSubCategoryLog;

    @Value("${log.deleted.subcategory}")
    private String deletedSubCategoryLog;

    @Value("${log.fetching.subcategory}")
    private String fetchingSubCategoryLog;

    @Value("${log.fetched.subcategory}")
    private String fetchedSubCategoryLog;

    @Value("${log.fetching.subcategories}")
    private String fetchingSubCategoriesLog;

    @Value("${log.fetched.subcategories}")
    private String fetchedSubCategoriesLog;
}

