package com.service.Inventory.Utils;

import lombok.Data;

@Data
public final class APIEndPoints {
    public static final String baseAPI = "/api/v1/";
    public static final String id = "/{id}";

    public static final String inventory = "inventory";
    public static final String inventories = "inventories";
    public static final String inventoryById = inventory+id;

}
