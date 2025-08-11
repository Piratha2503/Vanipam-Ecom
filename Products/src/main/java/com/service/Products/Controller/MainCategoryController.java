package com.service.Products.Controller;

import com.service.Products.APIResponse.APIContentResponse;
import com.service.Products.APIResponse.ApiBaseResponses;
import com.service.Products.APIResponse.ApiPaginatedContentResponse;
import com.service.Products.DTO.RequestDTO.MainCategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;
import com.service.Products.Enums.ResponseStatus;
import com.service.Products.Logging.MainCategoryLogs;
import com.service.Products.Service.MainCategoryService;
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
public class MainCategoryController {

    private final MainCategoryService mainCategoryService;
    private final ValidationCodesAndMessages validations;
    private final MainCategoryLogs logs;

    @GetMapping(mainCategoryById)
    public ResponseEntity<APIContentResponse<MainCategoryResponse>> getMainCategoryById(@PathVariable Long id) {

        log.info(logs.getFetchingMainCategoryLog());
        MainCategoryResponse response = mainCategoryService.getById(id);
        log.info(logs.getFetchedMainCategoryLog());

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getGetMainCategorySuccessMessage();

        return ResponseEntity.ok(new APIContentResponse<>(code, status, msg, mainCategory, response));
    }

    @GetMapping(mainCategories)
    public ResponseEntity<ApiPaginatedContentResponse<List<MainCategoryResponse>>> getAllMainCategories(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "sortField", required = false) String sortField) {

        log.info(logs.getFetchingMainCategoriesLog());

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

        List<MainCategoryResponse> list = mainCategoryService.getAll(pageable, pagination);

        log.info(logs.getFetchedMainCategoriesLog());

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getGetMainCategorySuccessMessage();

        return ResponseEntity.ok(new ApiPaginatedContentResponse<>(code, status, msg, mainCategories, list, pagination));
    }

    @PostMapping(mainCategory)
    public ResponseEntity<APIContentResponse<MainCategoryResponse>> createMainCategory(
            @Valid @RequestBody MainCategoryRequest dto) {

        log.info(logs.getCreatingMainCategoryLog(), dto);

        MainCategoryResponse response = mainCategoryService.create(dto);

        log.info(logs.getCreatedMainCategoryLog(), response);

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getSaveMainCategorySuccessMessage();

        return ResponseEntity.ok(new APIContentResponse<>(code, status, msg, mainCategory, response));
    }

    @PutMapping(mainCategory)
    public ResponseEntity<APIContentResponse<MainCategoryResponse>> updateMainCategory(
            @Valid @RequestBody MainCategoryRequest dto) {

        log.info(logs.getUpdatingMainCategoryLog(), dto);

        MainCategoryResponse response = mainCategoryService.update(dto);

        log.info(logs.getUpdatedMainCategoryLog(), response);

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getUpdateMainCategorySuccessMessage();

        return ResponseEntity.ok(new APIContentResponse<>(code, status, msg, mainCategory, response));
    }

    @DeleteMapping(mainCategoryById)
    public ResponseEntity<ApiBaseResponses> deleteMainCategory(@PathVariable Long id) {

        log.info(logs.getDeletingMainCategoryLog(), id);

        mainCategoryService.delete(id);

        log.info(logs.getDeletedMainCategoryLog(), id);

        String code = validations.getCommonSuccessCode();
        String status = ResponseStatus.SUCCESS.getStatus();
        String msg = validations.getDeleteMainCategorySuccessMessage();

        return ResponseEntity.ok(new ApiBaseResponses(code, status, msg));
    }


}