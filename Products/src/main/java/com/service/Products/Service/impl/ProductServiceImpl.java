package com.service.Products.Service.impl;

import com.service.Products.APIResponse.ApiPaginatedContentResponse;
import com.service.Products.DTO.RequestDTO.ProductSaveDTO;
import com.service.Products.DTO.RequestDTO.ProductUpdateDTO;
import com.service.Products.DTO.ResponseDTO.BrandResponse;
import com.service.Products.DTO.ResponseDTO.ProductResponseDTO;
import com.service.Products.DTO.ResponseDTO.ProductTypeResponse;
import com.service.Products.DTO.ResponseDTO.SubCategoryResponse;
import com.service.Products.Entities.Brand;
import com.service.Products.Entities.Product;
import com.service.Products.Entities.ProductType;
import com.service.Products.Entities.SubCategory;
import com.service.Products.ExceptionHandle.CustomExceptions.DuplicateValuesException;
import com.service.Products.Repositories.BrandRepository;
import com.service.Products.Repositories.ProductRepository;
import com.service.Products.Repositories.ProductTypeRepository;
import com.service.Products.Repositories.SubCategoryRepository;
import com.service.Products.Service.ProductService;
import com.service.Products.Utils.ValidationCodesAndMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ValidationCodesAndMessages validations;

    @Override
    public ProductResponseDTO create(ProductSaveDTO dto) {

        // Condition 1: Brand + Product Name in same SubCategory should not repeat
        if (productRepository.existsByProductNameAndBrandIdAndSubCategoryId(
                dto.productName(), dto.brandId(), dto.subCategoryId())) {
            throw new DuplicateValuesException("Product with this name already exists for the brand in this subcategory");
        }

        // Condition 2: Brand + Product Type + Product Name should be unique
        if (productRepository.existsByProductNameAndBrandIdAndIdNot(
                dto.productName(), dto.brandId(), dto.productType().id())) {
            throw new DuplicateValuesException("Product with this type already exists for the brand");
        }

        Brand brand = brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));

        SubCategory subCategory = subCategoryRepository.findById(dto.subCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found"));

        ProductType productType = productTypeRepository.findById(dto.productType().id())
                .orElseThrow(() -> new EntityNotFoundException("Product Type not found"));

        Product product = new Product();
        product.setProductName(dto.productName());
        product.setProductType(productType);
        product.setProductDescription(dto.productDescription());
        product.setBrand(brand);
        product.setUnitOfMeasure(dto.unitOfMeasure());
        product.setSubCategory(subCategory);
        product.setExpiryDate(dto.expiryDate());

        return productToProductResponse(productRepository.save(product));
    }


    @Override
    public ProductResponseDTO update(ProductUpdateDTO dto) {
        Product existing = productRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Long brandId = dto.brandId() != null ? dto.brandId() : existing.getBrand().getId();
        Long subCategoryId = dto.subCategoryId() != null ? dto.subCategoryId() : existing.getSubCategory().getId();
        Long productTypeId = dto.productType() != null ? dto.productType().id() : existing.getProductType().getId();
        String productName = dto.productName() != null ? dto.productName() : existing.getProductName();

        Brand brand = dto.brandId() != null
                ? brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"))
                : existing.getBrand();

        SubCategory subCategory = dto.subCategoryId() != null
                ? subCategoryRepository.findById(dto.subCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found"))
                : existing.getSubCategory();

        ProductType productType = dto.productType().id() != null
                ? productTypeRepository.findById(dto.productType().id())
                .orElseThrow(() -> new EntityNotFoundException("Product Type not found"))
                : existing.getProductType();


        if (productRepository.existsByProductNameAndBrandIdAndSubCategoryIdAndIdNot(
                productName, brandId, subCategoryId, dto.id())) {
            throw new DuplicateValuesException("Product with this name already exists for the brand in this subcategory");
        }


        if (productRepository.existsByProductNameAndBrandIdAndProductTypeIdAndIdNot(
                productName, brandId, productTypeId, dto.id())) {
            throw new DuplicateValuesException("Product with this type already exists for the brand");
        }


        existing.setProductName(productName);
        existing.setProductType(productType);
        existing.setProductDescription(dto.productDescription() != null ? dto.productDescription() : existing.getProductDescription());
        existing.setBrand(brand);
        existing.setUnitOfMeasure(dto.unitOfMeasure() != null ? dto.unitOfMeasure() : existing.getUnitOfMeasure());
        existing.setSubCategory(subCategory);
        existing.setExpiryDate(dto.expiryDate() != null ? dto.expiryDate() : existing.getExpiryDate());

        return productToProductResponse(productRepository.save(existing));
    }


    @Override
    public ProductResponseDTO getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productToProductResponse(product);
    }

    @Override
    public List<ProductResponseDTO> getAll(Pageable pageable, ApiPaginatedContentResponse.Pagination pagination) {
        Page<Product> products = productRepository.findAll(pageable);
        pagination.setTotalPages(products.getTotalPages());
        pagination.setTotalRecords(products.getTotalElements());

        return products.get().map(this::productToProductResponse).toList();
    }

    @Override
    public void delete(Long id) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        productRepository.delete(existing);
    }

    private ProductResponseDTO productToProductResponse(Product product) {

        ProductTypeResponse productType = new ProductTypeResponse(
                product.getProductType().getId(),
                product.getProductType().getName());

        BrandResponse brand = BrandResponse.builder().id(product.getBrand().getId())
                .brandName(product.getBrand().getBrandName()).build();

        SubCategoryResponse subCategory = new SubCategoryResponse(
                product.getSubCategory().getId(),
                product.getSubCategory().getSubCategoryName(),
                product.getSubCategory().getMainCategory().getId(),
                product.getSubCategory().getMainCategory().getMainCategoryName());

        return new ProductResponseDTO(
                product.getId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getUnitOfMeasure(),
                product.getExpiryDate(),
                product.getCreated_timestamp(),
                product.getUpdated_timestamp(),
                productType,
                brand,
                subCategory);
    }
}
