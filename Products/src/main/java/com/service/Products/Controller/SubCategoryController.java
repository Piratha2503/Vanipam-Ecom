package com.service.Products.Controller;

import com.service.Products.APIResponse.APIContentResponse;
import com.service.Products.APIResponse.ApiBaseResponses;
import com.service.Products.APIResponse.ApiPaginatedContentResponse;
import com.service.Products.DTO.RequestDTO.SubCategoryRequest;
import com.service.Products.DTO.ResponseDTO.SubCategoryResponse;
import com.service.Products.Enums.ResponseStatus;
import com.service.Products.Logging.SubCategoryLogs;
import com.service.Products.Service.SubCategoryService;
import com.service.Products.Utils.APIEndPoints;
import com.service.Products.Utils.ValidationCodesAndMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.service.Products.Utils.APIEndPoints.*;

@RestController
@RequestMapping(APIEndPoints.baseAPI)
@RequiredArgsConstructor
@Validated
@Slf4j
public class SubCategoryController {

    private final SubCategoryService subCategoryService;
    private final ValidationCodesAndMessages validations;
    private final SubCategoryLogs logs;

    private static final String SUB_CATEGORY = "subCategory";
    private static final String SUB_CATEGORIES = "subCategories";

    @GetMapping(subCategoryByID)
    public ResponseEntity<APIContentResponse<SubCategoryResponse>> getSubCategoryById(@PathVariable Long id) {

        long t0 = System.currentTimeMillis();
        log.info(logs.getFetchingSubCategoryLog(), id);

        SubCategoryResponse response = subCategoryService.getById(id);

        long t1 = System.currentTimeMillis() - t0;
        log.info(logs.getFetchedSubCategoryLog(), id, t1);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(),
                        ResponseStatus.SUCCESS.getStatus(),
                        validations.getGetSubCategorySuccessMessage(),
                        SUB_CATEGORY,
                        response
                )
        );
    }

    @GetMapping(subCategories)
    public ResponseEntity<ApiPaginatedContentResponse<List<SubCategoryResponse>>> getAllSubCategories(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "sortField", required = false) String sortField) {

        long t0 = System.currentTimeMillis();
        log.info(logs.getFetchingSubCategoriesLog());

        int pageNo = page != null ? page : 0;
        int pageSize = size != null ? size : 10;
        Sort.Direction sortDir = (direction != null)
                ? Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.ASC)
                : Sort.Direction.ASC;
        String sortBy = (sortField != null) ? sortField : "id";

        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDir, sortBy);
        ApiPaginatedContentResponse.Pagination pagination =
                ApiPaginatedContentResponse.Pagination.builder()
                        .pageNumber(pageNo)
                        .pageSize(pageSize)
                        .totalPages(0)
                        .totalRecords(0L)
                        .build();

        List<SubCategoryResponse> list = subCategoryService.getAll(pageable, pagination);

        long t1 = System.currentTimeMillis() - t0;
        log.info(logs.getFetchedSubCategoriesLog(), t1);

        return ResponseEntity.ok(
                new ApiPaginatedContentResponse<>(
                        validations.getCommonSuccessCode(),
                        ResponseStatus.SUCCESS.getStatus(),
                        validations.getGetSubCategorySuccessMessage(),
                        SUB_CATEGORIES,
                        list,
                        pagination
                )
        );
    }

    @PostMapping(subCategory)
    public ResponseEntity<APIContentResponse<SubCategoryResponse>> createSubCategory(
            @Valid @RequestBody SubCategoryRequest dto) {

        long t0 = System.currentTimeMillis();
        log.info(logs.getCreatingSubCategoryLog());

        SubCategoryResponse response = subCategoryService.create(dto);

        long t1 = System.currentTimeMillis() - t0;
        log.info(logs.getCreatedSubCategoryLog(), response.id(), t1);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(),
                        ResponseStatus.SUCCESS.getStatus(),
                        validations.getSaveSubCategorySuccessMessage(),
                        SUB_CATEGORY,
                        response
                )
        );
    }

    @PutMapping(subCategory)
    public ResponseEntity<APIContentResponse<SubCategoryResponse>> updateSubCategory(
            @Valid @RequestBody SubCategoryRequest dto) {

        long t0 = System.currentTimeMillis();
        log.info(logs.getUpdatingSubCategoryLog(), dto.id());

        SubCategoryResponse response = subCategoryService.update(dto);

        long t1 = System.currentTimeMillis() - t0;
        log.info(logs.getUpdatedSubCategoryLog(), response.id(), t1);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(),
                        ResponseStatus.SUCCESS.getStatus(),
                        validations.getUpdateSubCategorySuccessMessage(),
                        SUB_CATEGORY,
                        response
                )
        );
    }

    @DeleteMapping(subCategoryByID)
    public ResponseEntity<ApiBaseResponses> deleteSubCategory(@PathVariable Long id) {

        long t0 = System.currentTimeMillis();
        log.info(logs.getDeletingSubCategoryLog(), id);

        subCategoryService.delete(id);

        long t1 = System.currentTimeMillis() - t0;
        log.info(logs.getDeletedSubCategoryLog(), id, t1);

        return ResponseEntity.ok(
                new ApiBaseResponses(
                        validations.getCommonSuccessCode(),
                        ResponseStatus.SUCCESS.getStatus(),
                        validations.getDeleteSubCategorySuccessMessage()
                )
        );
    }
}
