package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

/*
|---------------------------------------------------------------------------------
| Abstract methods detailing what actions you can perform while hiding how the
| actions are performed/implemented
|---------------------------------------------------------------------------------
*/
public interface ProductService {
    /*
    |----------------------------------------------------
    | Add a product
    |----------------
    | Steps:
    |  - ProductService to ProductServiceImplementation
    |----------------------------------------------------
    */
    ResponseEntity<String> createProduct(ProductDetailsRequest productDetailsRequest);

    /*
    |----------------------------------------------------
    | Update a product
    |----------------
    | Steps:
    |  - ProductService to ProductServiceImplementation
    |----------------------------------------------------
    */
    ResponseEntity<String> updateProduct(Long product_id, ProductDetailsRequest productDetailsRequest);

    /*
    |----------------------------------------------------
    | Get/retrieve a product by id
    |----------------
    | Steps:
    |  - ProductService to ProductServiceImplementation
    |----------------------------------------------------
    */
    ProductEntity getProductById(Long product_id);
}