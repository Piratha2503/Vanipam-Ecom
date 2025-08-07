package com.service.Inventory.Service;


import com.service.Inventory.DTO.RequestDTO.InventoryRequestDTO;
import com.service.Inventory.DTO.ResponseDTO.InventoryResponseDTO;

import java.util.List;

public interface InventoryService {

    InventoryResponseDTO create(InventoryRequestDTO dto);

    InventoryResponseDTO getById(Long id);

    List<InventoryResponseDTO> getAll();

    InventoryResponseDTO update(Long id, InventoryRequestDTO dto);

    void delete(Long id);
}
