package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    |  - Use ProductEntity getId method to grab the id
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

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to update the product
    |----------------
    | Steps:
    |  - Create variables to hold the pass by reference values
    |  - Use productDetailsRequest get methods to retrieve information
    |    and store in variables.
    |  - Check to see if product is in the database
    |  - If the product exists, update its Retail_price, Discounted_price, and
    |    Availability then return HTTP response code 200
    |  - If product does not exist return HTTP response code 400
    |--------------------------------------------------------------------------
    */
    public ResponseEntity<String> updateProduct(Long product_id , ProductDetailsRequest productDetailsRequest){

        ProductEntity idDuplicateCheck = repository.findById(product_id).orElse(null);

        if(idDuplicateCheck == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            idDuplicateCheck.setName(productDetailsRequest.getName());
            idDuplicateCheck.setCategory(productDetailsRequest.getCategory());
            idDuplicateCheck.setRetail_price(productDetailsRequest.getRetail_price());
            idDuplicateCheck.setDiscounted_price(productDetailsRequest.getDiscounted_price());
            idDuplicateCheck.setAvailability(productDetailsRequest.getAvailability());

            repository.save(idDuplicateCheck);

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @Override
    public ProductEntity getProductById(Long product_id) {

        ProductEntity idDuplicateCheck = repository.findById(product_id).orElse(null);

        /*
        |---------------------------------------------------------------------------------
        | If product with the requested ID does not exists then the HTTP response code
        | should be 404; otherwise, the response code should be 200
        |---------------------------------------------------------------------------------
        */
        if(idDuplicateCheck == null) {
            return null;
            //new ResponseEntity(HttpStatus.NOT_FOUND)
        }
        else{
            return idDuplicateCheck;
        }
    }
}