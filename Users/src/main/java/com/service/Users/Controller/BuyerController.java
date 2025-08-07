package com.service.Users.Controller;

import com.service.Users.DTO.RequestDTO.BuyerRequest;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;
import com.service.Users.Service.BuyerService;
import com.service.Users.Utils.APIEndPoints;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.service.Users.Utils.APIEndPoints.*;

@RestController
@RequestMapping(APIEndPoints.baseAPI)
@RequiredArgsConstructor
@Slf4j
public class BuyerController {

    private final BuyerService buyerService;

    @PostMapping(buyer)
    public ResponseEntity<BuyerResponse> createBuyer(@Valid @RequestBody BuyerRequest request) {
        log.info("Creating buyer: {}", request);
        return ResponseEntity.ok(buyerService.createBuyer(request));
    }

    @GetMapping(buyers)
    public ResponseEntity<List<BuyerResponse>> getAllBuyers() {
        return ResponseEntity.ok(buyerService.getAllBuyers());
    }

    @GetMapping(buyer)
    public ResponseEntity<BuyerResponse> getBuyer(@RequestParam(value = "id",required = false) Long id,
                                                  @RequestParam(value = "mobile",required = false) String mobile,
                                                  @RequestParam(value = "userName",required = false) String userName) {

        return ResponseEntity.ok(buyerService.getBuyerById(id));
    }

    @PutMapping(buyerById)
    public ResponseEntity<BuyerResponse> updateBuyer(@PathVariable Long id, @Valid @RequestBody BuyerRequest request) {
        return ResponseEntity.ok(buyerService.updateBuyer(id, request));
    }

    @DeleteMapping(buyerById)
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        buyerService.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }
}
