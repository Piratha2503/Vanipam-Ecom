package com.service.Products.Controller;

import com.service.Products.APIResponse.APIContentResponse;
import com.service.Products.APIResponse.ApiBaseResponses;
import com.service.Products.APIResponse.ApiPaginatedContentResponse;
import com.service.Products.DTO.RequestDTO.ProductSaveDTO;
import com.service.Products.DTO.RequestDTO.ProductUpdateDTO;
import com.service.Products.DTO.ResponseDTO.ProductResponseDTO;
import com.service.Products.Enums.ResponseStatus;
import com.service.Products.Logging.ProductLogs;
import com.service.Products.Service.ProductService;
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
@Slf4j
@Validated
public class ProductController {

    private final ProductService productService;

    private final ValidationCodesAndMessages validations;

    private final ProductLogs logMessages;

    private final String status = ResponseStatus.SUCCESS.getStatus();

    @GetMapping(productById)
    public ResponseEntity<APIContentResponse<ProductResponseDTO>> getProduct(@PathVariable Long id) {

        log.info(logMessages.getFetchingProductLog());
        ProductResponseDTO productResponse = productService.getById(id);
        log.info(logMessages.getFetchedProductLog());

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(), status,
                        validations.getGetProductSuccessMessage(),
                        product,
                        productResponse
                )
        );
    }

    @GetMapping(products)
    public ResponseEntity<ApiPaginatedContentResponse<List<ProductResponseDTO>>> getAllProducts(@RequestParam(name = "page", required = false) Integer page,
                                                                                             @RequestParam(name = "size", required = false) Integer size,
                                                                                             @RequestParam(name = "direction", required = false) String direction,
                                                                                             @RequestParam(name = "sortField", required = false) String sortField) {
        log.info(logMessages.getFetchingProductsLog());

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

        var productsList = productService.getAll(pageable, pagination);
        log.info(logMessages.getFetchedProductsLog());

        return ResponseEntity.ok(
                new ApiPaginatedContentResponse<>(
                        validations.getCommonSuccessCode(), status,
                        validations.getGetProductSuccessMessage(),
                        products,
                        productsList,
                        pagination
                )
        );
    }

    @PostMapping(product)
    public ResponseEntity<APIContentResponse<ProductResponseDTO>> createProduct(
            @Valid @RequestBody ProductSaveDTO productRequest) {

        log.info(logMessages.getCreatingProductLog(), productRequest);
        ProductResponseDTO savedProduct = productService.create(productRequest);
        log.info(logMessages.getCreatedProductLog());

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(), status,
                        validations.getSaveProductSuccessMessage(),
                        product,
                        savedProduct
                )
        );
    }

    @PutMapping(product)
    public ResponseEntity<APIContentResponse<ProductResponseDTO>> updateProduct(
            @Valid @RequestBody ProductUpdateDTO dto) {

        log.info(logMessages.getUpdatingProductLog(), dto);
        ProductResponseDTO updatedProduct = productService.update(dto);
        log.info(logMessages.getUpdatedProductLog());

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(), status,
                        validations.getUpdateProductSuccessMessage(),
                        product,
                        updatedProduct
                )
        );
    }

    @DeleteMapping(productById)
    public ResponseEntity<ApiBaseResponses> deleteProduct(@PathVariable Long id) {
        log.info(logMessages.getDeletingProductLog(), id);
        productService.delete(id);
        log.info(logMessages.getDeletedProductLog(), id);

        return ResponseEntity.ok(
                new ApiBaseResponses(
                        validations.getCommonSuccessCode(),
                        status,
                        validations.getDeleteProductSuccessMessage()));
    }
}
