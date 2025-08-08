package com.service.Users.Enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ResponseStatus {
    SUCCESS("success"), FAILURE("failure"), UNKNOWN("unknown"), ERROR("error"), WARNING("warning");

    private final String status;

    ResponseStatus(String status) {
        this.status = status;
    }

    public static ResponseStatus getByStatus(String status) {
        return Arrays.stream(values())
                .filter(responseStatus -> responseStatus.getStatus().equals(status))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Request status not found for given status [status: " + status + "]"));
    }
}
