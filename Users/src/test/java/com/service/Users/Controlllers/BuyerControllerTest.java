package com.service.Users.Controlllers;

import com.service.Users.APIResponse.APIContentResponse;
import com.service.Users.Controller.BuyerController;
import com.service.Users.DTO.RequestDTO.BuyerSaveDTO;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;
import com.service.Users.Entities.Address;
import com.service.Users.Logging.BuyerLogs;
import com.service.Users.Service.BuyerService;
import com.service.Users.Utils.APIEndPoints;
import com.service.Users.Utils.ValidationCodesAndMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuyerControllerTest {

    @Mock
    private BuyerService buyerService;

    @Mock
    private ValidationCodesAndMessages validations;

    @Mock
    private BuyerLogs logMessages;

    @InjectMocks
    private BuyerController buyerController;

    @Test
    void createBuyer_shouldReturnOkResponse() {
        BuyerSaveDTO dto = new BuyerSaveDTO(
                "John", "Doe", "john@example.com", "+12345678901",
                new Address(), "johndoe", "pass123"
        );

        BuyerResponse response = new BuyerResponse(
                1L, "John", "Doe", "john@example.com",
                "+12345678901", new Address(), "johndoe",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        Map<String,BuyerResponse> map = Map.of(APIEndPoints.buyer,response);

        when(buyerService.create(dto)).thenReturn(response);

        ResponseEntity<APIContentResponse<BuyerResponse>> entity =
                buyerController.createBuyer(dto);

        Assertions.assertNotNull(entity.getBody());
        assertEquals(validations.getCommonSuccessCode(), entity.getBody().getValidation_Code());
        Assertions.assertNotNull(entity.getBody());
        assertEquals(validations.getSaveBuyerSuccessMessage(), entity.getBody().getValidation_message());
        assertEquals(map, entity.getBody().getContents());
    }
}
