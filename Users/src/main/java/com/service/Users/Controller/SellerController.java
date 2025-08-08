package com.service.Users.Controller;

import com.service.Users.APIResponse.APIContentResponse;
import com.service.Users.APIResponse.ApiBaseResponses;
import com.service.Users.DTO.RequestDTO.SellerSaveDTO;
import com.service.Users.DTO.RequestDTO.SellerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.SellerResponse;
import com.service.Users.Service.SellerService;
import com.service.Users.Utils.APIEndPoints;
import com.service.Users.Utils.ValidationCodesAndMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final String status = com.service.Users.Enums.ResponseStatus.SUCCESS.getStatus();

    @PostMapping(seller)
    public ResponseEntity<APIContentResponse<SellerResponse>> createSeller(
            @Valid @RequestBody SellerSaveDTO sellerRequest) {

        log.info("Creating seller: {}", sellerRequest);
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

    @GetMapping(sellerById)
    public ResponseEntity<APIContentResponse<SellerResponse>> getSeller(@PathVariable Long id) {
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
    public ResponseEntity<APIContentResponse<Iterable<SellerResponse>>> getAllSellers() {
        var sellersList = sellerService.getAll();
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

    @PutMapping(seller)
    public ResponseEntity<APIContentResponse<SellerResponse>> updateSeller(
            @Valid @RequestBody SellerUpdateDTO dto) {

        log.info("Updating seller: {}", dto);
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
        log.info("Deleting seller by id : {}", id);
        sellerService.delete(id);
        return ResponseEntity.ok(
                new ApiBaseResponses(
                        status,
                        validations.getCommonSuccessCode(),
                        validations.getDeleteSellerSuccessMessage()));
    }
}

