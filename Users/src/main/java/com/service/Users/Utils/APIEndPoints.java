package com.service.Users.Utils;

import lombok.Data;

@Data
public final class APIEndPoints {
    public static final String baseAPI = "/api/v1/";
    public static final String id = "/{id}";

    public static final String buyer = "buyer";
    public static final String buyers = "buyers";
    public static final String buyerById = "buyer"+id;

    public static final String seller = "seller";
    public static final String sellers = "sellers";
    public static final String sellerById = "seller"+id;
}
