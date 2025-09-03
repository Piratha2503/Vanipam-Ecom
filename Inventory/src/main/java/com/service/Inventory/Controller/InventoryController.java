package com.service.Inventory.Controller;

import com.service.Inventory.APIResponse.APIContentResponse;
import com.service.Inventory.APIResponse.ApiBaseResponses;
import com.service.Inventory.DTO.RequestDTO.InventoryRequestDTO;
import com.service.Inventory.DTO.ResponseDTO.InventoryResponseDTO;
import com.service.Inventory.Enums.ResponseStatus;
import com.service.Inventory.Service.InventoryService;
import com.service.Inventory.Utils.APIEndPoints;
import com.service.Inventory.Utils.ValidationCodesAndMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.service.Inventory.Utils.APIEndPoints.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Validated
@RequestMapping(APIEndPoints.baseAPI)
@Slf4j
public class InventoryController {

    private final InventoryService service;
    private final ValidationCodesAndMessages validations;

    String status = ResponseStatus.SUCCESS.getStatus();

    @GetMapping(inventoryById)
    public ResponseEntity<APIContentResponse<InventoryResponseDTO>> get(@PathVariable Long id) {
        InventoryResponseDTO inventoryResponse = service.getById(id);
        return ResponseEntity.ok(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(),
                        status,
                        validations.getGetInventorySuccessMessage(),
                        inventory,
                        inventoryResponse)
        );
    }

    @GetMapping(inventories)
    public ResponseEntity<APIContentResponse<List<InventoryResponseDTO>>> getAll() {
        List<InventoryResponseDTO> inventoryResponseList = service.getAll();
        return ResponseEntity.ok(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(),
                        status,
                        validations.getGetInventorySuccessMessage(),
                        inventories,
                        inventoryResponseList)
        );
    }

    @PostMapping(inventory)
    public ResponseEntity<APIContentResponse<InventoryResponseDTO>> create(@Valid @RequestBody InventoryRequestDTO dto) {

        log.info("Saving Inventory: {}", dto);
        InventoryResponseDTO saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(),
                        status,
                        validations.getSaveInventorySuccessMessage(),
                        inventory,
                        saved)
        );
    }

    @PutMapping(inventoryById)
    public ResponseEntity<APIContentResponse<InventoryResponseDTO>> update(@PathVariable Long id,
                                                                           @Valid @RequestBody InventoryRequestDTO dto) {
        log.info("Updating Inventory: {}", dto);
        InventoryResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(
                new APIContentResponse<>(
                        validations.getCommonSuccessCode(),
                        status,
                        validations.getUpdateInventorySuccessMessage(),
                        inventory,
                        updated)
        );
    }

    @DeleteMapping(inventoryById)
    public ResponseEntity<ApiBaseResponses> delete(@PathVariable Long id) {
        log.info("Deleting Inventory: {}", id);
        service.delete(id);
        return ResponseEntity.ok(
                new ApiBaseResponses(
                        validations.getCommonSuccessCode(),
                        status,
                        validations.getDeleteInventorySuccessMessage())
        );
    }
}
