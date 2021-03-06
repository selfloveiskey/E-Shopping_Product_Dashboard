package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
|--------------------------------------------------------------------------
| Details how the abstract methods from ProductService are implemented
|--------------------------------------------------------------------------
*/
@Service
@Transactional
public class ServiceImpl implements ProductService{

    @Autowired
    ProductRepository repository;

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to create the product
    |----------------
    | Steps:
    |  - Create an instance of ProductEntity & pass in productDetails
    |  - Create a new instance of ProductEntity to store productDetails once found
    |  - Use productEntity getId method to grab the id
    |  - Pass id to ProductRepository CRUD findById method
    |  - Store result in idDuplicateCheck
    | - If product with same ID already exists then the HTTP response code should
    |   be 400 otherwise, the response code should be 201
    |--------------------------------------------------------------------------
    */
    @Override
    public ResponseEntity<ProductEntity> createProduct(ProductDetailsRequest productDetailsRequest) {

        ProductEntity productEntity = new ProductEntity(productDetailsRequest);
        Optional<ProductEntity> idDuplicateCheck = repository.findById(productEntity.getId());

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
    |  - Create a new instance of ProductEntity to store productDetails once found
    |  - Pass in product_id to ProductRepository CRUD findById method to see if
    |    the product is in the database
    |  - Store result in idDuplicateCheck
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

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to return the product by id
    |----------------
    | Steps:
    |  - Create a new instance of ProductEntity to store productDetails once found
    |  - Pass in product_id to ProductRepository CRUD findById method to see if
    |    the product is in the database
    |  - Store result in idDuplicateCheck
    | - If product with the requested ID does not exists then the HTTP response code
    |   should be 404; otherwise, the response code should be 200
    |--------------------------------------------------------------------------
    */
    @Override
    public ProductEntity getProductById(Long product_id) {

        ProductEntity idDuplicateCheck = repository.findById(product_id).orElse(null);

//        System.out.println(idDuplicateCheck.getName());

        if(idDuplicateCheck == null) {
            return null;
        }
        else{
            return idDuplicateCheck;
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to return the product by category
    |----------------
    | Steps:
    |  - Create a new instance of ProductEntity to store productDetails once found
    |  - Pass in product_id to ProductRepository CRUD findById method to see if
    |    the product is in the database
    |  - Store result in idDuplicateCheck
    |  - If product with the requested ID does not exists then the HTTP response code
    |    should be 404; otherwise, the response code should be 200
    |--------------------------------------------------------------------------

    |--------------------------------------------------------------------------
    | Requirements
    |--------------
    | - Return JSON array of all products by the given category using GET request
    | - HTTP response should be 200
    | - JSON array should be sorted by availability
    | - In stock products must be listed before out of stock products
    | - Products with same availability must be sorted by discount price in
    |   ascending order
    | - Products with same discount price must be sorted by ID in ascending order
    |--------------------------------------------------------------------------
    */
    @Override
    public List<ProductEntity> getProductByCategory(String category) {

        List<ProductEntity> productList = repository.findAllByCategory(category);

        List<ProductEntity> sortedProductList = productList.stream()
                                                .sorted(Comparator.comparing(ProductEntity::getAvailability).reversed())
                                                .collect(Collectors.toList());

        if(sortedProductList == null) {
            return null;
        }
        else{
            return sortedProductList;
        }
    }

    /*
    |----------------------------------------------------------------------------------
    | Tell your program how its going to return the product by category & availability
    |----------------
    | Steps:
    |  -
    |  -
    |  -
    |  -
    |  - The response code should be 200
    |----------------------------------------------------------------------------------

    |--------------------------------------------------------------------------
    | Requirements
    |--------------
    | - Return JSON array of all products by the given category and availability
    |   using GET request
    | - HTTP response should be 200
    | - JSON array should be sorted by discount percentage in descending order
    | - Products with same discount percentage status must be sorted by discount
    |   price in ascending order.
    | - Products with same discount price must be sorted by ID in ascending order
    | Note: Discount percentage is always an integer
    |--------------------------------------------------------------------------
    */
}