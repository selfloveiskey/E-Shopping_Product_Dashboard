package com.hackerrank.eshopping.product.dashboard.repository;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
/*
|--------------------------------------------------------
| Takes ProductEntity class and queries the database
|--------------------------------------------------------
*/
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    /*
    |---------------------------------------------------------------------------
    | If the record is found the database it will create a ProductEntity object
    | and return it to the ProductServiceImplementation file
    |---------------------------------------------------------------------------
    */

      Optional<ProductEntity> findById(Long id);
}