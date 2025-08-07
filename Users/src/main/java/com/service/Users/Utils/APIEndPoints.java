package com.service.Users.Utils;

import lombok.Data;

@Data
public final class APIEndPoints {
    public static final String baseAPI = "/api/v1/";
    public static final String id = "/{id}";

    public static final String buyer = "buyer";
    public static final String buyers = "buyers";
    public static final String buyerById = "buyer"+id;
}
