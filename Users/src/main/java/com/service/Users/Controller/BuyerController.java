package com.service.Users.Controller;

import com.service.Users.APIResponse.APIContentResponse;
import com.service.Users.APIResponse.ApiBaseResponses;
import com.service.Users.APIResponse.ApiPaginatedContentResponse;
import com.service.Users.DTO.RequestDTO.BuyerSaveDTO;
import com.service.Users.DTO.RequestDTO.BuyerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;
import com.service.Users.Service.BuyerService;
import com.service.Users.Utils.APIEndPoints;
import com.service.Users.Utils.ValidationCodesAndMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static com.service.Users.Utils.APIEndPoints.*;

@RestController
@RequestMapping(APIEndPoints.baseAPI)
@RequiredArgsConstructor
@Slf4j
@Validated
public class BuyerController {

    private final BuyerService buyerService;
    private final ValidationCodesAndMessages validations;

    private final String status = com.service.Users.Enums.ResponseStatus.SUCCESS.getStatus();

    @GetMapping(buyerById)
    public ResponseEntity<APIContentResponse<BuyerResponse>> getBuyer(@PathVariable Long id) {
        BuyerResponse buyerResponse = buyerService.getById(id);
        return ResponseEntity.ok(
                new APIContentResponse<>(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getGetBuyerSuccessMessage(),
                        buyer,
                        buyerResponse
                )
        );
    }

    @GetMapping(buyers)
    public ResponseEntity<APIContentResponse<Iterable<BuyerResponse>>> getAllBuyers(@RequestParam(name = "page",required = false) Integer page,
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

        var buyersList = buyerService.getAll(pageable,pagination);
        return ResponseEntity.ok(
                new ApiPaginatedContentResponse<>(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getGetBuyerSuccessMessage(),
                        buyers,
                        buyersList,
                        pagination)
        );
    }

    @PostMapping(buyer)
    public ResponseEntity<APIContentResponse<BuyerResponse>> createBuyer(
            @Valid @RequestBody BuyerSaveDTO buyerRequest) {

        log.info("Creating buyer: {}", buyerRequest);
        BuyerResponse savedBuyer = buyerService.create(buyerRequest);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getSaveBuyerSuccessMessage(),
                        buyer,
                        savedBuyer
                )
        );
    }

    @PutMapping(buyer)
    public ResponseEntity<APIContentResponse<BuyerResponse>> updateBuyer(
            @Valid @RequestBody BuyerUpdateDTO dto) {

        log.info("Updating buyer: {}", dto);
        BuyerResponse updatedBuyer = buyerService.update(dto);
        return ResponseEntity.ok(
                new APIContentResponse<>(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getUpdateBuyerSuccessMessage(),
                        buyer,
                        updatedBuyer
                )
        );
    }

    @DeleteMapping(buyerById)
    public ResponseEntity<ApiBaseResponses> deleteBuyer(@PathVariable Long id) {
        log.info("Deleting buyer by id : {}", id);
        buyerService.delete(id);
        return ResponseEntity.ok(
                new ApiBaseResponses(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getDeleteBuyerSuccessMessage())
        );
    }
}
