package com.service.Users.Controlllers;

import com.service.Users.APIResponse.APIContentResponse;
import com.service.Users.APIResponse.ApiBaseResponses;
import com.service.Users.APIResponse.ApiPaginatedContentResponse;
import com.service.Users.Controller.BuyerController;
import com.service.Users.DTO.RequestDTO.BuyerSaveDTO;
import com.service.Users.DTO.RequestDTO.BuyerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.BuyerResponse;
import com.service.Users.Entities.Address;
import com.service.Users.Enums.ResponseStatus;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    void getBuyerById_shouldReturnOkResponse() {

        BuyerResponse response = new BuyerResponse(
                2L, "John", "Doe", "john@example.com",
                "+12345678901", new Address(), "johndoe",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        Map<String,BuyerResponse> map = Map.of(APIEndPoints.buyer,response);

        when(buyerService.getById(2L)).thenReturn(response);

        ResponseEntity<APIContentResponse<BuyerResponse>> entity =
                buyerController.getBuyer(2L);


        Assertions.assertNotNull(entity.getBody());
        assertEquals(validations.getCommonSuccessCode(), entity.getBody().getValidation_Code());

        Assertions.assertNotNull(entity.getBody());
        assertEquals(ResponseStatus.SUCCESS.getStatus(),entity.getBody().getValidation_status());

        Assertions.assertNotNull(entity.getBody());
        assertEquals(validations.getGetBuyerSuccessMessage(), entity.getBody().getValidation_message());

        Assertions.assertNotNull(entity.getBody());
        assertEquals(map, entity.getBody().getContents());
    }

    @Test
    void getBuyers_shouldReturnOkResponse() {

        List<BuyerResponse> mockBuyersList = List.of(
                new BuyerResponse(
                        1L, "John", "Doe", "john@example.com", "+12345678901",
                        new Address(), "johndoe",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())
                ),
                new BuyerResponse(
                        2L, "Jane", "Smith", "jane@example.com", "+12345678902",
                        new Address(), "janesmith",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())
                )
        );

        Map<String,List<BuyerResponse>> map = Map.of(APIEndPoints.buyers,mockBuyersList);

        when(buyerService.getAll(any(Pageable.class), any(ApiPaginatedContentResponse.Pagination.class))).thenReturn(mockBuyersList);

        ResponseEntity<ApiPaginatedContentResponse<List<BuyerResponse>>> entity =
                buyerController.getAllBuyers(0,10, Sort.Direction.ASC.name(),"id");


        Assertions.assertNotNull(entity.getBody());
        assertEquals(validations.getCommonSuccessCode(), entity.getBody().getValidation_Code());

        Assertions.assertNotNull(entity.getBody());
        assertEquals(ResponseStatus.SUCCESS.getStatus(),entity.getBody().getValidation_status());

        Assertions.assertNotNull(entity.getBody());
        assertEquals(validations.getGetBuyerSuccessMessage(), entity.getBody().getValidation_message());

        Assertions.assertNotNull(entity.getBody());
        assertEquals(map, entity.getBody().getContents());

//        Assertions.assertNotNull(entity.getBody().getPagination());
//        assertEquals(ApiPaginatedContentResponse.Pagination.builder()
//                .pageNumber(0)
//                .totalPages(10)
//                .pageSize(10)
//                .totalRecords(100L)
//                .build(),entity.getBody().getPagination());
    }

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

    @Test
    void updateBuyer_shouldReturnOkResponse() {
        BuyerUpdateDTO dto = new BuyerUpdateDTO(
                1L,"John", "Doe", "john@example.com", "+12345678901",
                new Address(), "johne", "pass123"
        );

        BuyerResponse response = new BuyerResponse(
                1L, "John", "Doe", "john@example.com",
                "+12345678901", new Address(), "johne",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        Map<String,BuyerResponse> map = Map.of(APIEndPoints.buyer,response);

        when(buyerService.update(dto)).thenReturn(response);

        ResponseEntity<APIContentResponse<BuyerResponse>> entity =
                buyerController.updateBuyer(dto);

        Assertions.assertNotNull(entity.getBody());
        assertEquals(validations.getCommonSuccessCode(), entity.getBody().getValidation_Code());
        Assertions.assertNotNull(entity.getBody());
        assertEquals(validations.getSaveBuyerSuccessMessage(), entity.getBody().getValidation_message());
        assertEquals(map, entity.getBody().getContents());
    }

    @Test
    void deleteBuyer_shouldReturnOkResponse() {
      Long buyerId = 1L;

        doNothing().when(buyerService).delete(buyerId);

        ResponseEntity<ApiBaseResponses> entity =
                buyerController.deleteBuyer(buyerId);

        verify(buyerService, times(1)).delete(buyerId);

        Assertions.assertNotNull(entity.getBody());
        assertEquals(100, entity.getBody().getValidation_Code());
        Assertions.assertNotNull(entity.getBody());
        assertEquals(ResponseStatus.SUCCESS.getStatus(),entity.getBody().getValidation_status());
        Assertions.assertNotNull(entity.getBody());
        assertEquals(validations.getDeleteBuyerSuccessMessage(), entity.getBody().getValidation_message());

    }
}
