package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;
import com.hackerrank.eshopping.product.dashboard.repository.ProductRepository;
import com.hackerrank.eshopping.product.dashboard.calculations.ProductCalculations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    ProductCalculations productCalculations = new ProductCalculations();

    public Double retail_price;
    public Double discounted_price;
    public Integer discounted_percentage;

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to create the product
    |----------------
    | Steps:
    |  - Create an instance of ProductEntity & pass in productDetailsRequest
    |  - Use productEntity getId method to get the id then
    |  - Pass id to ProductRepository CRUD findById method to query the database
    |  - Use Optional object of productEntity type idDuplicateCheck to store results
    |  - If product with same ID already exists then the HTTP response code should
    |    be 400.
    |  - If product does not already exist, calculate and set the
    |    discounted_percentage before saving details in the database.
    |  - Lastly, the response code should be 201
    |--------------------------------------------------------------------------
    */
    @Override
    public ResponseEntity<ProductEntity> createProduct(ProductDetailsRequest productDetailsRequest) {

        ProductEntity productEntity = new ProductEntity(productDetailsRequest);
        Optional<ProductEntity> idDuplicateCheck = repository.findById(productEntity.getId());

        if(idDuplicateCheck.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            if(productEntity != null){
               retail_price = productEntity.getRetail_price();
               discounted_price = productEntity.getDiscounted_price();
               discounted_percentage = productCalculations.calculateDiscountPercentage(retail_price, discounted_price);
               productEntity.setDiscounted_percentage(discounted_percentage);
            }
            repository.save(productEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to update the product
    |----------------
    | Steps:
    |  - Pass in product_id to ProductRepository CRUD findById method to query
    |    the database to see if the product exist.
    |  - Use new instance of ProductEntity idDuplicateCheck to store results
    |  - If product does not exist return HTTP response code 400
    |  - If the product exists, update its Retail_price, Discounted_price, and
    |    Availability.
    |  - Recalculate and set the discounted_percentage before saving details
    |    in the database.
    |  - Lastly, return HTTP response code 200
    |--------------------------------------------------------------------------
    */
    public ResponseEntity<String> updateProduct(Long product_id , ProductDetailsRequest productDetailsRequest){

        ProductEntity idDuplicateCheck = repository.findById(product_id).orElse(null);

        if(idDuplicateCheck == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            idDuplicateCheck.setRetail_price(productDetailsRequest.getRetail_price());
            idDuplicateCheck.setDiscounted_price(productDetailsRequest.getDiscounted_price());
            idDuplicateCheck.setAvailability(productDetailsRequest.getAvailability());

            retail_price = idDuplicateCheck.getRetail_price();
            discounted_price = idDuplicateCheck.getDiscounted_price();
            discounted_percentage = productCalculations.calculateDiscountPercentage(retail_price, discounted_price);
            idDuplicateCheck.setDiscounted_percentage(discounted_percentage);

            repository.save(idDuplicateCheck);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to return the product by id
    |----------------
    | Steps:
    |  - Pass product_id to ProductRepository CRUD findById method to
    |    query the database for the product
    |  - Return results
    |--------------------------------------------------------------------------
    */
    @Override
    public ProductEntity getProductById(Long product_id) {

        return repository.findById(product_id).orElse(null);
    }

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to return the product by category
    |----------------
    | Steps:
    |  - Pass in category to ProductRepository findAllByCategory method to
    |    query the database for the product.
    |  - Create an ArrayList object of ProductEntity type to store results
    |  - Produce a new stream with elements sorted by availability, discount
    |    price, and ID.
    |  - Create a stream object of ProductEntity type to store results
    |  - Convert sortedProductList to a list then assign to a new list of
    |    ProductEntity type.
    |  - Create a new ArrayList and initialize it with details from sortedList
    |  - Return arrayList
    |--------------------------------------------------------------------------

    |--------------------------------------------------------------------------
    | Requirements
    |--------------
    | - Return JSON array of all products by the given category using GET request
    | - HTTP response should be 200
    | - JSON array should be sorted by availability. Meaning in stock products
    |   must be listed before out of stock products.
    | - Products with same availability must be sorted by discount price in
    |   ascending order
    | - Products with same discount price must be sorted by ID in ascending order
    |--------------------------------------------------------------------------
    */
    @Override
    public ArrayList<ProductEntity> getProductByCategory(String category) {

        ArrayList<ProductEntity> productList = repository.findAllByCategory(category);

        Stream<ProductEntity> sortedProductList = productList.stream().sorted(Comparator.comparing(ProductEntity::getAvailability)
                                                                                        .reversed()
                                                                                        .thenComparing(ProductEntity::getDiscounted_price)
                                                                                        .thenComparing(ProductEntity::getId));

        List<ProductEntity> sortedList = sortedProductList.collect(Collectors.toList());
        ArrayList<ProductEntity> arrayList = new ArrayList<ProductEntity>(sortedList);

        return arrayList;
    }

    /*
    |----------------------------------------------------------------------------------
    | Tell your program how its going to return the product by category & availability
    |----------------
    | Steps:
    |  - Clean up category then store it in a new string
    |  - Pass in category and availability to ProductRepository
	|    findAllByCategoryAndAvailability method to query the database for the product.
    |  - Create an ArrayList object of ProductEntity type to store results
    |  - Produce a new stream with elements sorted by discount percentage,
    |    discount price, and ID.
    |  - Create a stream object of ProductEntity type to store results
    |  - Convert sortedProductList to a list then assign to a new list of
    |    ProductEntity type.
    |  - Create a new ArrayList and initialize it with details from sortedList
    |  - Return arrayList
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
    @Override
    public ArrayList<ProductEntity> findProductByCategoryAndAvailability(String category, Boolean availability) {

        String category1 = category.replace("%20"," ");

        ArrayList<ProductEntity> productList = repository.findAllByCategoryAndAvailability(category1,availability);

        Stream<ProductEntity> sortedProductList = productList.stream().sorted(Comparator.comparing(ProductEntity::getDiscounted_percentage)
                                                                                        .reversed()
                                                                                        .thenComparing(ProductEntity::getDiscounted_price)
                                                                                        .thenComparing(ProductEntity::getId));

        List<ProductEntity> sortedList = sortedProductList.collect(Collectors.toList());

        ArrayList<ProductEntity> arrayList = new ArrayList<ProductEntity>(sortedList);

        return arrayList;
    }

    /*
    |--------------------------------------------------------------------------
    | Tell your program how its going to return all products
    |----------------
    | Steps:
    |  - Use ProductRepository CRUD findAll method to query the database for
    |    all products.
    |  - Save results to a List object of ProductEntity type
    |  - Produce a new stream with elements sorted by ID.
    |  - Create a stream object of ProductEntity type to store results
    |  - Convert sortedProductList to a list then assign to a new list of
    |    ProductEntity type.
    |  - Create a new ArrayList and initialize it with details from sortedList
    |  - Return arrayList
    |--------------------------------------------------------------------------
    */
    @Override
    public ArrayList<ProductEntity> getAllProducts() {

        List<ProductEntity> productList = (List<ProductEntity>) repository.findAll();

        Stream<ProductEntity> sortedProductList = productList.stream().sorted(Comparator.comparing(ProductEntity::getId));

        List<ProductEntity> sortedList = sortedProductList.collect(Collectors.toList());
        ArrayList<ProductEntity> arrayList = new ArrayList<ProductEntity>(sortedList);

        return arrayList;
    }
}