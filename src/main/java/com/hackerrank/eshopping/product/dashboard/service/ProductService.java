package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import org.springframework.http.ResponseEntity;
import java.util.List;

/*
|---------------------------------------------------------------------------------
| Abstract methods detailing what actions you can perform while hiding how the
| actions are performed/implemented
|---------------------------------------------------------------------------------
*/
public interface ProductService {
    /*
    |----------------------------------------------------
    | Add product
    |----------------
    | Next steps:
    |  - ProductService to ProductServiceImplementation
    |----------------------------------------------------
    */
    ResponseEntity<ProductEntity> createProduct(ProductDetailsRequest productDetailsRequest);

    /*
    |----------------------------------------------------
    | Update product
    |----------------
    | Next steps:
    |  - ProductService to ProductServiceImplementation
    |----------------------------------------------------
    */
    ResponseEntity<String> updateProduct(Long product_id, ProductDetailsRequest productDetailsRequest);

    /*
    |----------------------------------------------------
    | Get product by id
    |----------------
    | Next steps:
    |  - ProductService to ProductServiceImplementation
    |----------------------------------------------------
    */
    ProductEntity getProductById(Long product_id);

    /*
    |----------------------------------------------------
    | Return product(s) by category
    |----------------
    | Next steps:
    |  - ProductService to ProductServiceImplementation
    |----------------------------------------------------
    */
    List<ProductEntity> getProductByCategory(String category);

    /*
    |----------------------------------------------------
    | Return product(s) by category and availability
    |----------------
    | Next steps:
    |  - ProductService to ProductServiceImplementation
    |----------------------------------------------------
    */

}