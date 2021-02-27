package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<String> createProduct(ProductDetailsRequest productDetailsReques);
}