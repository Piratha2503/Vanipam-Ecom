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
    private final ValidationCodesAndMessages validationMessages;

    @Override
    public ProductResponseDTO create(ProductSaveDTO dto) {

        if (productRepository.existsByProductNameAndBrandId(dto.productName(), dto.brandId()))
            throw new DuplicateValuesException("Product with this name already exists for the brand");

        Brand brand = brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));

        SubCategory subCategory = subCategoryRepository.findById(dto.subCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found"));

        Product product = new Product();
        product.setProductName(dto.productName());
        product.setProductType(ProductType.builder().id(product.getProductType().getId())
                .name(product.getProductType().getName()).build());
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

        if (dto.productName() != null && dto.brandId() != null
                && productRepository.existsByProductNameAndBrandIdAndIdNot(dto.productName(), dto.brandId(), dto.id())) {
            throw new DuplicateValuesException("Product with this name already exists for the brand");
        }

        Brand brand = dto.brandId() != null ? brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found")) : existing.getBrand();

        SubCategory subCategory = dto.subCategoryId() != null ? subCategoryRepository.findById(dto.subCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found")) : existing.getSubCategory();

        existing.setProductName(dto.productName() != null ? dto.productName() : existing.getProductName());
        //existing.setProductType(dto.productType() != null ? dto.productType() : existing.getProductType());
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
