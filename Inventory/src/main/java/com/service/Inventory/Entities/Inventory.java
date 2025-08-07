package com.service.Inventory.Entities;

import com.service.Inventory.Enums.InventoryStatus;
import com.service.Inventory.Utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

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
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    private Long sellerId;

    @Enumerated(EnumType.STRING)
    private InventoryStatus status;

    private LocalDateTime createdAt;
}
