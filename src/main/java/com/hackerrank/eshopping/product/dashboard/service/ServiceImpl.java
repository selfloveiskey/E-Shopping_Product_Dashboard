package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
/*
|--------------------------------------------------------------------------
| Details how the abstract methods from ProductService are implemented
|--------------------------------------------------------------------------
*/
@Service
public class ServiceImpl implements ProductService{

    @Autowired
    ProductRepository repository;

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to create the product
    |----------------
    | Steps:
    |  - Create an instance of ProductEntity to store productDetails
    |  - Use ProductEntity to create a instance to store productDetails
    |  - Use ProductEntity getId to grab the id
    |  - Pass id to ProductRepository CRUD findById method
    |  - Store result in idDuplicateCheck
    |--------------------------------------------------------------------------
    */
    @Override
    public ResponseEntity<String> createProduct(ProductDetailsRequest productDetailsRequest) {
        ProductEntity productEntity = new ProductEntity(productDetailsRequest);

        Optional<ProductEntity> idDuplicateCheck = repository.findById(productEntity.getId());

        /*
        |---------------------------------------------------------------------------------
        | If product with same ID already exists then the HTTP response code should be 400
        | otherwise, the response code should be 201
        |---------------------------------------------------------------------------------
        */
        if(idDuplicateCheck.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            repository.save(productEntity);
            return new ResponseEntity(HttpStatus.CREATED);
        }

    }
}