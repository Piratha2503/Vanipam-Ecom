package com.service.Products.Utils.Enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ResponseStatus {
    SUCCESS("success"), FAILURE("failure"), UNKNOWN("unknown"), ERROR("Error"), WARNING("warning");

    private String status;

    ResponseStatus(String status) {
        this.status = status;
    }

    public static ResponseStatus getByStatus(String status)
    {

        for (ResponseStatus responseStatus : values())
        {
            if (responseStatus.getStatus().equals(status))
            {
                return responseStatus;
            }
        }


        throw new AssertionError("Request status not found for given status [status: " + status + "]");
    }
}
