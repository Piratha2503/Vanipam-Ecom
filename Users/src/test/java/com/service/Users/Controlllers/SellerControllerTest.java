package com.service.Users.Controlllers;

import com.service.Users.APIResponse.APIContentResponse;
import com.service.Users.APIResponse.ApiBaseResponses;
import com.service.Users.APIResponse.ApiPaginatedContentResponse;
import com.service.Users.Controller.SellerController;
import com.service.Users.DTO.RequestDTO.SellerSaveDTO;
import com.service.Users.DTO.RequestDTO.SellerUpdateDTO;
import com.service.Users.DTO.ResponseDTO.SellerResponse;
import com.service.Users.Enums.ResponseStatus;
import com.service.Users.Logging.SellerLogs;
import com.service.Users.Service.SellerService;
import com.service.Users.Utils.APIEndPoints;
import com.service.Users.Utils.ValidationCodesAndMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerControllerTest {

    @Mock
    private SellerService sellerService;

    @Mock
    private ValidationCodesAndMessages validations;

    @Mock
    private SellerLogs logMessages;

    @InjectMocks
    private SellerController sellerController;

    @Test
    void getSellerById_shouldReturnOkResponse() {
        SellerResponse response = new SellerResponse(
                1L, "Alice", "Wonder", "alice@example.com",
                "+12345678999", null, "alicew","seller",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));

        Map<String, SellerResponse> map = Map.of(APIEndPoints.seller, response);

        when(sellerService.getById(1L)).thenReturn(response);

        ResponseEntity<APIContentResponse<SellerResponse>> entity = sellerController.getSeller(1L);

        assertNotNull(entity.getBody());
        assertEquals(validations.getCommonSuccessCode(), entity.getBody().getValidation_Code());
        assertEquals(ResponseStatus.SUCCESS.getStatus(), entity.getBody().getValidation_status());
        assertEquals(validations.getGetSellerSuccessMessage(), entity.getBody().getValidation_message());
        assertEquals(map, entity.getBody().getContents());

        verify(sellerService, times(1)).getById(1L);
    }

    @Test
    void getAllSellers_shouldReturnOkResponse() {
        List<SellerResponse> sellersList = List.of(
                new SellerResponse(1L, "Alice", "Wonder", "alice@example.com", "+12345678999", null, "alicew",
                        "seller1",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())),
                new SellerResponse(1L, "Alice", "Wonder", "alice@example.com", "+12345678999", null, "alicew",
                        "seller2",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())));

        Map<String, List<SellerResponse>> map = Map.of(APIEndPoints.sellers, sellersList);

        when(sellerService.getAll(any(Pageable.class), any(ApiPaginatedContentResponse.Pagination.class)))
                .thenReturn(sellersList);

        ResponseEntity<ApiPaginatedContentResponse<List<SellerResponse>>> entity =
                sellerController.getAllSellers(0, 10, Sort.Direction.ASC.name(), "id");

        assertNotNull(entity.getBody());
        assertEquals(validations.getCommonSuccessCode(), entity.getBody().getValidation_Code());
        assertEquals(ResponseStatus.SUCCESS.getStatus(), entity.getBody().getValidation_status());
        assertEquals(validations.getGetSellerSuccessMessage(), entity.getBody().getValidation_message());
        assertEquals(map, entity.getBody().getContents());

        verify(sellerService, times(1)).getAll(any(Pageable.class), any(ApiPaginatedContentResponse.Pagination.class));
    }

    @Test
    void createSeller_shouldReturnOkResponse() {
        SellerSaveDTO dto = new SellerSaveDTO( "Alice", "Wonder", "alice@example.com", "+12345678999","seller1", null,
                "alicew","pass@123");

        SellerResponse response = new SellerResponse(1L, "Alice", "Wonder", "alice@example.com", "+12345678999", null, "alicew",
                "seller1",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));

        Map<String, SellerResponse> map = Map.of(APIEndPoints.seller, response);

        when(sellerService.create(dto)).thenReturn(response);

        ResponseEntity<APIContentResponse<SellerResponse>> entity = sellerController.createSeller(dto);

        assertNotNull(entity.getBody());
        assertEquals(validations.getCommonSuccessCode(), entity.getBody().getValidation_Code());
        assertEquals(ResponseStatus.SUCCESS.getStatus(), entity.getBody().getValidation_status());
        assertEquals(validations.getSaveSellerSuccessMessage(), entity.getBody().getValidation_message());
        assertEquals(map, entity.getBody().getContents());

        verify(sellerService, times(1)).create(dto);
    }

    @Test
    void updateSeller_shouldReturnOkResponse() {
        SellerUpdateDTO dto = new SellerUpdateDTO(1L, "Alicee", "Wonder", "alice@example.com", "+12345678999","seller1", null,
                "alicew","pass@123");

        SellerResponse response = new SellerResponse(1L, "Alicee", "Wonder", "alice@example.com", "+12345678999", null, "alicew",
                "seller1",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));

        Map<String, SellerResponse> map = Map.of(APIEndPoints.seller, response);

        when(sellerService.update(dto)).thenReturn(response);

        ResponseEntity<APIContentResponse<SellerResponse>> entity = sellerController.updateSeller(dto);

        assertNotNull(entity.getBody());
        assertEquals(validations.getCommonSuccessCode(), entity.getBody().getValidation_Code());
        assertEquals(ResponseStatus.SUCCESS.getStatus(), entity.getBody().getValidation_status());
        assertEquals(validations.getUpdateSellerSuccessMessage(), entity.getBody().getValidation_message());
        assertEquals(map, entity.getBody().getContents());

        verify(sellerService, times(1)).update(dto);
    }

    @Test
    void deleteSeller_shouldReturnOkResponse() {
        Long sellerId = 1L;

        doNothing().when(sellerService).delete(sellerId);

        ResponseEntity<ApiBaseResponses> entity = sellerController.deleteSeller(sellerId);

        verify(sellerService, times(1)).delete(sellerId);

        assertNotNull(entity.getBody());
        assertEquals(validations.getCommonSuccessCode(), entity.getBody().getValidation_Code());
        assertEquals(ResponseStatus.SUCCESS.getStatus(), entity.getBody().getValidation_status());
        assertEquals(validations.getDeleteSellerSuccessMessage(), entity.getBody().getValidation_message());
    }
}
