package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import org.springframework.http.ResponseEntity;
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
}