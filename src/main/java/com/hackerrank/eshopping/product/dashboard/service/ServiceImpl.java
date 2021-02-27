package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;
@Service
public class ServiceImpl implements ProductService{
    @Autowired
    ProductRepository repository;
    @Override
    public ResponseEntity<String> createProduct(ProductDetailsRequest productDetailsRequest) {
        ProductEntity product = new ProductEntity(productDetailsRequest);

        Optional<ProductEntity> returnedProduct = repository.findById(productDetailsRequest.getId());

        if(returnedProduct.isPresent())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            repository.save(product);
            return new ResponseEntity(HttpStatus.CREATED);
        }

    }
}