package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     | Add product(s)
     |----------------
     | Next steps:
     |  - Controller to ProductService
     |---------------------------------
     */
    @PostMapping( consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductDetailsRequest productDetailsRequest){

        return productService.createProduct(productDetailsRequest);
    }

    /*
    |-----------------------------------
    | Update product(s) by id
    |----------------
    | Next steps:
    |  - Controller to ProductService
    |----------------------------------
    */
    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDetailsRequest productDetailsRequest){

        return productService.updateProduct(id, productDetailsRequest);
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
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseEntity<ProductEntity> getProductByCategory(@RequestParam String category){

        ArrayList<ProductEntity> productEntity = productService.getProductByCategory(category);

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

    @GetMapping(params = {"category", "availability"},produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseEntity<ProductEntity> getProductByCategoryAndAvailability(@RequestParam String category,@RequestParam String availability){

        ArrayList<ProductEntity> productEntity = new ArrayList<ProductEntity>();

        if(availability.equals("1")){
             productEntity = productService.findProductByCategoryAndAvailability(category,true);
        }
        else if(availability.equals("0")){
             productEntity = productService.findProductByCategoryAndAvailability(category,false);
        }

        if(productEntity == null){
            return new ResponseEntity<ProductEntity>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(productEntity, HttpStatus.OK);
        }
    }

    /*
    |-----------------------------------
    | Return all products
    |----------------
    | Next steps:
    |  - Controller to ProductService
    |----------------------------------
    */
    @GetMapping(params = {"!category"},produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ProductEntity> getAllProduct(){

        ArrayList<ProductEntity> productEntity = productService.getAllProducts();

        if(productEntity == null){
            return new ResponseEntity<ProductEntity>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(productEntity, HttpStatus.OK);
        }
    }
}