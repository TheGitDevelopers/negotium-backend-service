package com.negotium.negotiumapp.controller;

import com.negotium.negotiumapp.model.warehouse.ProductDto;
import com.negotium.negotiumapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "findAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> findAll(@RequestParam(required = false) String name) {
        if (name != null) {
            return productService.findAllByNameContaining(name);
        }
        return productService.findAll();
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product cannot have an id yet");
        }
        ProductDto savedProduct = productService.addProduct(productDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedProduct);
    }

    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        if (!id.equals(productDto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product id must match with id in resource path");
        }
        ProductDto updatedProduct = productService.updateProduct(productDto);
        return ResponseEntity.ok(updatedProduct);
    }
}