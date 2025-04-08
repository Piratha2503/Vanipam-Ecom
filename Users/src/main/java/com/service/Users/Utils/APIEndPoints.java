package com.service.Users.Utils;

import lombok.Data;

@Data
public final class APIEndPoints {
    public static final String baseAPI = "/api/users/";
    public static final String id = "/{id}";

    // Buyer
    public static final String saveBuyer    = "saveBuyer";
    public static final String getBuyerList = "getBuyerList";
    public static final String updateBuyer  = "updateBuyer";
    public static final String deleteBuyer  = "deleteBuyer";
    public static final String getBuyerByID = "getBuyerByID"+id;

    // Seller
    public static final String saveSeller    = "saveSeller";
    public static final String getSellerList = "getSellerList";
    public static final String updateSeller  = "updateSeller";
    public static final String deleteSeller  = "deleteSeller";
    public static final String getSellerById = "getSellerById"+id;
}
