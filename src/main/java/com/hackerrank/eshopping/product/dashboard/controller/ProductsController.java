package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

   @Autowired
   ProductService productService;

    @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createProduct(@RequestBody ProductDetailsRequest productDetailsRequest){

        return productService.createProduct(productDetailsRequest);
    }

}