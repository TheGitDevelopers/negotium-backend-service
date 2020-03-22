package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.exception.DuplicateProductIdException;
import com.negotium.negotiumapp.exception.ProductRemoveException;
import com.negotium.negotiumapp.model.warehouse.Product;
import com.negotium.negotiumapp.model.warehouse.ProductDto;
import com.negotium.negotiumapp.model.warehouse.ProductMapper;
import com.negotium.negotiumapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name)
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(ProductMapper::toDto);
    }

    public void deleteById(Long id) {
        Optional<Product> foundProductById = productRepository.findById(id);
        foundProductById.ifPresentOrElse(
                product -> productRepository.deleteById(id),
                () -> {
                    throw new ProductRemoveException("Product could not be deleted. Wrong id");
                });
    }

    public void deleteByProductIndex(int index) {
        Optional<Product> foundProductByIndex = productRepository.findByProductIndex(index);
        foundProductByIndex.ifPresentOrElse(
                product -> productRepository.deleteProductByProductIndex(index),
                () -> {
                    throw new ProductRemoveException("Product could not be deleted. Wrong index");
                }
        );
    }

    public ProductDto addProduct(ProductDto productDto) {
        Optional<Product> foundByProductId = productRepository.findById(productDto.getId());
        foundByProductId.ifPresent(x -> {
            throw new DuplicateProductIdException("Product with this ID is already exist");
        });

        Optional<Product> foundProductByIndex = productRepository.findByProductIndex(productDto.getProductIndex());
        foundProductByIndex.orElseThrow(
                () -> {
                    throw new NullPointerException("Product not found");
                }
        );

        return mapAndSaveRequest(productDto);
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Long id = productDto.getId();

        Optional<Product> foundByProductId =
                productRepository.findById(id);
        if (foundByProductId.isEmpty()) {
            throw new NullPointerException("Product not found");
        }
        foundByProductId.ifPresent(product -> {
            if (!(product.getId()).equals(id)) {
                throw new DuplicateProductIdException("Product with this ID is already exist");
            }
        });

        return mapAndSaveRequest(productDto);
    }

    private ProductDto mapAndSaveRequest(ProductDto productDto) {
        Product product = ProductMapper.toEntity(productDto);
        Product savedRequest = productRepository.save(product);
        return ProductMapper.toDto(savedRequest);
    }
}
