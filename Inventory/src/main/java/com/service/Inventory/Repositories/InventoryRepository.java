package com.service.Inventory.Repositories;

import com.service.Inventory.Entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {}
