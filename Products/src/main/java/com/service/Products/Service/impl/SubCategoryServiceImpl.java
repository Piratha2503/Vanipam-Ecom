package com.service.Products.Service.impl;

import com.service.Products.APIResponse.ApiPaginatedContentResponse;
import com.service.Products.DTO.RequestDTO.SubCategoryRequest;
import com.service.Products.DTO.ResponseDTO.SubCategoryResponse;
import com.service.Products.Entities.MainCategory;
import com.service.Products.Entities.SubCategory;
import com.service.Products.Repositories.MainCategoryRepository;
import com.service.Products.Repositories.SubCategoryRepository;
import com.service.Products.Service.SubCategoryService;
import com.service.Products.Utils.ValidationCodesAndMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final ValidationCodesAndMessages validations;

    @Override
    public SubCategoryResponse create(SubCategoryRequest dto) {
        if (subCategoryRepository.existsBySubCategoryNameIgnoreCase(dto.subCategoryName())) {
            throw new IllegalArgumentException(validations.getSubCategoryAlreadyExistMessage().replace("{0}", dto.subCategoryName()));
        }

        MainCategory mainCategory = mainCategoryRepository.findById(dto.mainCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(validations.getMainCategoryEntityNotFoundMessage()+" "+dto.mainCategoryId()));

        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryName(dto.subCategoryName());
        subCategory.setMainCategory(mainCategory);

        SubCategory saved = subCategoryRepository.save(subCategory);

        return mapToResponse(saved);
    }

    @Override
    public SubCategoryResponse update(SubCategoryRequest dto) {
        SubCategory existing = subCategoryRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException(validations.getSubCategoryEntityNotFoundMessage()+" "+ dto.id()));

        if (dto.subCategoryName() != null && !dto.subCategoryName().isBlank()) {
            existing.setSubCategoryName(dto.subCategoryName());
        }

        if (dto.mainCategoryId() != null) {
            MainCategory mainCategory = mainCategoryRepository.findById(dto.mainCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException(validations.getMainCategoryEntityNotFoundMessage()+" "+dto.mainCategoryId()));
            existing.setMainCategory(mainCategory);
        }

        SubCategory updated = subCategoryRepository.save(existing);

        return mapToResponse(updated);
    }

    @Override
    public SubCategoryResponse getById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(validations.getSubCategoryEntityNotFoundMessage()+" " + id));
        return mapToResponse(subCategory);
    }

    @Override
    public List<SubCategoryResponse> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination) {
        Page<SubCategory> page = subCategoryRepository.findAll(pageable);
        pagination.setTotalPages(page.getTotalPages());
        pagination.setTotalRecords(page.getTotalElements());
        return page.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        SubCategory existing = subCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(validations.getSubCategoryEntityNotFoundMessage()+" " + id));
        subCategoryRepository.delete(existing);
    }

    private SubCategoryResponse mapToResponse(SubCategory subCategory) {
        return new SubCategoryResponse(
                subCategory.getId(),
                subCategory.getSubCategoryName(),
                subCategory.getMainCategory().getId(),
                subCategory.getMainCategory().getMainCategoryName()
        );
    }


}
