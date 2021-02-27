package com.hackerrank.eshopping.product.dashboard.repository;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    public Optional<ProductEntity> findById(Long id);
}