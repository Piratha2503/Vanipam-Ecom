package com.service.Inventory.Entities;

import com.service.Inventory.Enums.InventoryStatus;
import com.service.Inventory.Utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory extends DateTimeUtils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "price_per_unit", nullable = false)
    private BigDecimal pricePerUnit;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private Long productId;

    @Enumerated(EnumType.STRING)
    private InventoryStatus status;
}
