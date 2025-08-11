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

    @GetMapping(subCategoryByID)
    public ResponseEntity<APIContentResponse<SubCategoryResponse>> getSubCategoryById(@PathVariable Long id) {

        log.info(logs.getFetchingSubCategoryLog());
        SubCategoryResponse response = subCategoryService.getById(id);
        log.info(logs.getFetchedSubCategoryLog());

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getGetSubCategorySuccessMessage();

        return ResponseEntity.ok(new APIContentResponse<>(code, status, msg, subCategory, response));
    }

    @GetMapping(subCategories)
    public ResponseEntity<ApiPaginatedContentResponse<List<SubCategoryResponse>>> getAllSubCategories(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "sortField", required = false) String sortField) {

        log.info(logs.getFetchingSubCategoriesLog());

        int pageNo = page != null ? page : 0;
        int pageSize = size != null ? size : 10;
        Sort.Direction sortDir = (direction != null)
                ? Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.ASC)
                : Sort.Direction.ASC;
        String sortBy = (sortField != null) ? sortField : "id";

        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDir, sortBy);
        ApiPaginatedContentResponse.Pagination pagination = ApiPaginatedContentResponse.Pagination.builder()
                .pageNumber(pageNo)
                .pageSize(pageSize)
                .totalPages(0)
                .totalRecords(0L)
                .build();

        List<SubCategoryResponse> list = subCategoryService.getAll(pageable, pagination);

        log.info(logs.getFetchedSubCategoriesLog());

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getGetSubCategorySuccessMessage();

        return ResponseEntity.ok(new ApiPaginatedContentResponse<>(code, status, msg, subCategories, list, pagination));
    }

    @PostMapping(subCategory)
    public ResponseEntity<APIContentResponse<SubCategoryResponse>> createSubCategory(
            @Valid @RequestBody SubCategoryRequest dto) {

        log.info(logs.getCreatingSubCategoryLog(), dto);

        SubCategoryResponse response = subCategoryService.create(dto);

        log.info(logs.getCreatedSubCategoryLog(), response);

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getSaveSubCategorySuccessMessage();

        return ResponseEntity.ok(new APIContentResponse<>(code, status, msg, subCategory, response));
    }

    @PutMapping(subCategory)
    public ResponseEntity<APIContentResponse<SubCategoryResponse>> updateSubCategory(
            @Valid @RequestBody SubCategoryRequest dto) {

        log.info(logs.getUpdatingSubCategoryLog(), dto);

        SubCategoryResponse response = subCategoryService.update(dto);

        log.info(logs.getUpdatedSubCategoryLog(), response);

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getUpdateSubCategorySuccessMessage();

        return ResponseEntity.ok(new APIContentResponse<>(code, status, msg, subCategory, response));
    }

    @DeleteMapping(subCategoryByID)
    public ResponseEntity<ApiBaseResponses> deleteSubCategory(@PathVariable Long id) {

        log.info(logs.getDeletingSubCategoryLog(), id);

        subCategoryService.delete(id);

        log.info(logs.getDeletedSubCategoryLog(), id);

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getDeleteSubCategorySuccessMessage();

        return ResponseEntity.ok(new ApiBaseResponses(code, status, msg));
    }
}
