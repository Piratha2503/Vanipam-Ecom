package com.service.Products.Service.impl;

import com.service.Products.APIResponse.ApiPaginatedContentResponse;
import com.service.Products.DTO.RequestDTO.MainCategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;
import com.service.Products.Entities.MainCategory;
import com.service.Products.Repositories.MainCategoryRepository;
import com.service.Products.Service.MainCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainCategoryServiceImpl implements MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;

    @Override
    public MainCategoryResponse create(MainCategoryRequest dto) {
        if (mainCategoryRepository.existsByMainCategoryNameIgnoreCase(dto.mainCategoryName())) {
            throw new IllegalArgumentException("MainCategory with name '" + dto.mainCategoryName() + "' already exists");
        }

        MainCategory mainCategory = new MainCategory();
        mainCategory.setMainCategoryName(dto.mainCategoryName());

        MainCategory saved = mainCategoryRepository.save(mainCategory);

        return mapToResponse(saved);
    }

    @Override
    public MainCategoryResponse update(MainCategoryRequest dto) {
        MainCategory existing = mainCategoryRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("MainCategory not found with id: " + dto.id()));

        if (dto.mainCategoryName() != null && !dto.mainCategoryName().isBlank()) {
            existing.setMainCategoryName(dto.mainCategoryName());
        }

        MainCategory updated = mainCategoryRepository.save(existing);

        return mapToResponse(updated);
    }

    @Override
    public MainCategoryResponse getById(Long id) {
        MainCategory mainCategory = mainCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MainCategory not found with id: " + id));
        return mapToResponse(mainCategory);
    }

    @Override
    public List<MainCategoryResponse> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination) {
        Page<MainCategory> page = mainCategoryRepository.findAll(pageable);
        pagination.setTotalPages(page.getTotalPages());
        pagination.setTotalRecords(pagination.getTotalRecords());
        return page.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        MainCategory existing = mainCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MainCategory not found with id: " + id));
        mainCategoryRepository.delete(existing);
    }

    private MainCategoryResponse mapToResponse(MainCategory mainCategory) {
        return new MainCategoryResponse( mainCategory.getId(), mainCategory.getMainCategoryName());
    }
}
