package com.service.Users.Controller;

import com.service.Users.APIResponse.APIContentResponse;
import com.service.Users.APIResponse.ApiBaseResponses;
import com.service.Users.APIResponse.ApiPaginatedContentResponse;
import com.service.Users.DTO.RequestDTO.BuyerSaveDTO;
import com.service.Users.DTO.RequestDTO.BuyerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;
import com.service.Users.Enums.ResponseStatus;
import com.service.Users.Logging.BuyerLogs;
import com.service.Users.Service.BuyerService;
import com.service.Users.Utils.APIEndPoints;
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
public class BuyerController {

    private final BuyerService buyerService;
    private final ValidationCodesAndMessages validations;
    private final BuyerLogs logMessages;
    private final String status = ResponseStatus.SUCCESS.getStatus();

    @GetMapping(buyerById)
    public ResponseEntity<APIContentResponse<BuyerResponse>> getBuyer(@PathVariable Long id) {

        log.info(logMessages.getFetchingBuyerLog());
        BuyerResponse buyerResponse = buyerService.getById(id);
        log.info(logMessages.getFetchedBuyerLog());

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
    public ResponseEntity<APIContentResponse<Iterable<BuyerResponse>>> getAllBuyers(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "sortField", required = false) String sortField) {

        log.info(logMessages.getFetchingBuyersLog());

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

        var buyersList = buyerService.getAll(pageable, pagination);
        log.info(logMessages.getFetchedBuyersLog());

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getGetBuyerSuccessMessage(),
                        buyers,
                        buyersList
                )
        );
    }

    @PostMapping(buyer)
    public ResponseEntity<APIContentResponse<BuyerResponse>> createBuyer(
            @Valid @RequestBody BuyerSaveDTO buyerRequest) {

        log.info(logMessages.getCreatingBuyerLog(), buyerRequest);
        BuyerResponse savedBuyer = buyerService.create(buyerRequest);
        log.info(logMessages.getCreatedBuyerLog());

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

        log.info(logMessages.getUpdatingBuyerLog(), dto);
        BuyerResponse updatedBuyer = buyerService.update(dto);
        log.info(logMessages.getUpdatedBuyerLog());

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

        log.info(logMessages.getDeletingBuyerLog(), id);
        buyerService.delete(id);
        log.info(logMessages.getDeletedBuyerLog(), id);

        return ResponseEntity.ok(
                new ApiBaseResponses(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getDeleteBuyerSuccessMessage()
                )
        );
    }
}
