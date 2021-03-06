package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
     | Add product(s)
     |----------------
     | Next steps:
     |  - Controller to ProductService
     |---------------------------------
     */
    @PostMapping( consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductDetailsRequest productDetailsRequest){
        System.out.println("I made it to the ProductController class ^_^ " + " createProduct ");
        return productService.createProduct(productDetailsRequest);
    }

    /*
    |-----------------------------------
    | Update product(a) by id
    |----------------
    | Next steps:
    |  - Controller to ProductService
    |----------------------------------
    */
    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDetailsRequest productDetailsRequest){
        System.out.println("I made it to the ProductController class ^_^ " + " updateProduct ");
        return productService.updateProduct(id, productDetailsRequest);
        //return productService.updateProduct(id, retail_price, discounted_price, availability);
    }

    /*
    |-----------------------------------
    | Return product(s) by id
    |----------------
    | Next steps:
    |  - Controller to ProductService
    |----------------------------------
    */
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ProductEntity> getProduct(@PathVariable Long id){

        ProductEntity productEntity = productService.getProductById(id);
        if(productEntity == null){
            return new ResponseEntity<ProductEntity>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(productEntity, HttpStatus.OK);
        }
    }

    /*
    |---------------------------------
    | Return product(s) by category
    |----------------
    | Next steps:
    |  - Controller to ProductService
    |---------------------------------
    */

    @GetMapping( produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseEntity<ProductEntity> getProductByCategory(@RequestParam String category){

        List<ProductEntity> productEntity = productService.getProductByCategory(category);
        if(productEntity == null){
            return new ResponseEntity<ProductEntity>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(productEntity, HttpStatus.OK);
        }
    }

    /*
    |------------------------------------------------
    | Return product(s) by category and availability
    |----------------
    | Next steps:
    |  - Controller to ProductService
    |------------------------------------------------
    */

}