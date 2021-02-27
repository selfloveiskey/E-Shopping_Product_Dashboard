package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.model.UpdateProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
        //Long product_id = id;
        return productService.updateProduct(id, productDetailsRequest);
    }

    /*
    |-------------------------------------------------------------------------
    | Return a product by id
    |-------------------------------------------------------------------------
    */
    @GetMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void getProduct(@PathVariable Long id){

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