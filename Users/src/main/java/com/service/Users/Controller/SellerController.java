package com.service.Users.Controller;

import com.service.Users.APIResponse.APIContentResponse;
import com.service.Users.APIResponse.ApiBaseResponses;
import com.service.Users.APIResponse.ApiPaginatedContentResponse;
import com.service.Users.DTO.RequestDTO.SellerSaveDTO;
import com.service.Users.DTO.RequestDTO.SellerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.SellerResponse;
import com.service.Users.Enums.ResponseStatus;
import com.service.Users.Service.SellerService;
import com.service.Users.Utils.APIEndPoints;
import com.service.Users.Utils.LogMessages;
import com.service.Users.Utils.ValidationCodesAndMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.service.Users.Utils.APIEndPoints.*;

@RestController
@RequestMapping(APIEndPoints.baseAPI)
@RequiredArgsConstructor
@Slf4j
@Validated
public class SellerController {

    private final SellerService sellerService;

    private final ValidationCodesAndMessages validations;

    private final LogMessages logMessages;

    private final String status = ResponseStatus.SUCCESS.getStatus();


    @GetMapping(sellerById)
    public ResponseEntity<APIContentResponse<SellerResponse>> getSeller(@PathVariable Long id) {
        log.info(logMessages.getFetchSellerLog(), id);
        SellerResponse sellerResponse = sellerService.getById(id);
        return ResponseEntity.ok(
                new APIContentResponse<>(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getGetSellerSuccessMessage(),
                        seller,
                        sellerResponse
                )
        );
    }

    @GetMapping(sellers)
    public ResponseEntity<APIContentResponse<Iterable<SellerResponse>>> getAllSellers(@RequestParam(name = "page",required = false) Integer page,
                                                                                      @RequestParam(name = "size",required = false) Integer size,
                                                                                      @RequestParam(name = "direction",required = false) String direction,
                                                                                      @RequestParam(name = "sortField",required = false) String sortField) {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.valueOf(direction.toUpperCase()),sortField);
        ApiPaginatedContentResponse.Pagination pagination = ApiPaginatedContentResponse.Pagination.builder()
                .pageNumber(page)
                .pageSize(size)
                .totalPages(0)
                .totalRecords(0L)
                .build();

        log.info(logMessages.getFetchSellersLog());

        var sellersList = sellerService.getAll(pageable,pagination);
        return ResponseEntity.ok(
                new APIContentResponse<>(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getGetSellerSuccessMessage(),
                        sellers,
                        sellersList
                )
        );
    }

    @PostMapping(seller)
    public ResponseEntity<APIContentResponse<SellerResponse>> createSeller(
            @Valid @RequestBody SellerSaveDTO sellerRequest) {

        log.info(logMessages.getCreateSellerLog(), sellerRequest);
        SellerResponse savedSeller = sellerService.create(sellerRequest);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getSaveSellerSuccessMessage(),
                        seller,
                        savedSeller
                )
        );
    }

    @PutMapping(seller)
    public ResponseEntity<APIContentResponse<SellerResponse>> updateSeller(
            @Valid @RequestBody SellerUpdateDTO dto) {

        log.info(logMessages.getUpdateSellerLog(), dto);
        SellerResponse updatedSeller = sellerService.update(dto);
        return ResponseEntity.ok(
                new APIContentResponse<>(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getUpdateSellerSuccessMessage(),
                        seller,
                        updatedSeller
                )
        );
    }

    @DeleteMapping(sellerById)
    public ResponseEntity<ApiBaseResponses> deleteSeller(@PathVariable Long id) {
        log.info(logMessages.getDeleteSellerLog(), id);
        sellerService.delete(id);
        return ResponseEntity.ok(
                new ApiBaseResponses(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getDeleteSellerSuccessMessage()));
    }
}

