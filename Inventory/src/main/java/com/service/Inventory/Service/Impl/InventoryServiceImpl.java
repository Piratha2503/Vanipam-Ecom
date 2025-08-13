package com.service.Inventory.Service.Impl;

import com.service.Inventory.DTO.RequestDTO.InventoryRequestDTO;
import com.service.Inventory.DTO.ResponseDTO.InventoryResponseDTO;
import com.service.Inventory.Entities.Inventory;
import com.service.Inventory.Repositories.InventoryRepository;
import com.service.Inventory.Service.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    @Override
    public InventoryResponseDTO create(InventoryRequestDTO dto) {
        Inventory inv = Inventory.builder()
                .quantity(dto.quantity())
                .status(dto.status())
                .build();
        return toDTO(repository.save(inv));
    }

    @Override
    public InventoryResponseDTO getById(Long id) {
        return toDTO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Inventory "+id +" Not found")));
    }

    @Override
    public List<InventoryResponseDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public InventoryResponseDTO update(Long id, InventoryRequestDTO dto) {
        Inventory inv = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Inventory "+id +" Not found"));
        inv.setProductId(dto.productId());
        inv.setSellerId(dto.sellerId());
        inv.setPricePerUnit(dto.pricePerUnit());
        inv.setQuantity(dto.quantity());
        inv.setStatus(dto.status());
        return toDTO(repository.save(inv));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private InventoryResponseDTO toDTO(Inventory inv) {
        return new InventoryResponseDTO(
                inv.getQuantity(),
                inv.getPricePerUnit(),
                inv.getSellerId(),
                inv.getProductId(),
                inv.getStatus()
        );
    }
}
