package com.mutabeni.store.repository;

import com.mutabeni.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long> {
    Optional<Product> findByName(String name);

    Product findBySerial(String serial);

    boolean existsBySerial(String serial);

    void deleteBySerial(String serial);
}
