package com.service.Inventory.Enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ApiResponseStatus {
    SUCCESS("Success"),
    FAILURE("Failure"),
    UNKNOWN("Unknown"),
    ERROR("Error"),
    WARNING("Warning");

    private final String status;

    ApiResponseStatus(String status) {
        this.status = status;
    }

    public static ApiResponseStatus getByStatus(String status) {
        return Arrays.stream(values())
                .filter(responseStatus -> responseStatus.getStatus().equals(status))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Request status not found for given status [status: " + status + "]"));
    }
}
