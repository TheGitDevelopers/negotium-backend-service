package com.negotium.negotiumapp.repository;

import com.negotium.negotiumapp.model.warehouse.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductIndex(Integer productIndex);

    List<Product> findAllByNameContaining(String name);

    void deleteProductByProductIndex(Integer index);
}
