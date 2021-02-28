package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.model.UpdateProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

/*
|-------------------------------------------------------------
| Responsible for all operations that have to do with products
|-------------------------------------------------------------
*/
@RestController
@RequestMapping("/products") //http://localhost:8080/products
public class ProductsController {

   @Autowired
   ProductService productService;

     /*
     |---------------------------------
     | Add a product
     |----------------
     | Steps:
     |  - Controller to ProductService
     |---------------------------------
     */
    @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createProduct(@RequestBody ProductDetailsRequest productDetailsRequest){

        return productService.createProduct(productDetailsRequest);
    }

    /*
    |-----------------------------------
    | Update a product by id
    |----------------
    | Steps:
    |  - Controller to ProductService
    |----------------------------------
    */
    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody  ProductDetailsRequest productDetailsRequest){
        return productService.updateProduct(id, productDetailsRequest);
    }

    /*
    |-----------------------------------
    | Get/retrieve a product by id
    |----------------
    | Steps:
    |  - Controller to ProductService
    |----------------------------------
    */
    @GetMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ProductEntity> getProduct(@PathVariable Long id, @RequestBody  ProductDetailsRequest productDetailsRequest){
         ProductEntity productEntity = productService.getProductById(id);
         if(productEntity == null){
            return new ResponseEntity<ProductEntity>(HttpStatus.NOT_FOUND);
        }
         else{
             return new ResponseEntity(productEntity, HttpStatus.OK);
         }
    }

    /*
    |-----------------------------
    | Return products by category
    |-----------------------------
    */



    /*
    |----------------------------------------------
    | Return products by category and availability
    |----------------------------------------------
    */

}