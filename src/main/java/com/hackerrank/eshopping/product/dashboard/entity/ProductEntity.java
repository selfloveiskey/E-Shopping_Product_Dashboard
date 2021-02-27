package com.hackerrank.eshopping.product.dashboard.entity;

import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "products")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 2949736079142298511L;

    @Id
    @GeneratedValue
    private long productID;

    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false)
    private Double retail_price;

    @Column(nullable = false)
    private Double discounted_price;

    @Column(nullable = false)
    private Boolean availability;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, String category, Double retail_price, Double discounted_price, Boolean availability) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.retail_price = retail_price;
        this.discounted_price = discounted_price;
        this.availability = availability;
    }

    public ProductEntity(ProductDetailsRequest productDetails) {
        this.id = productDetails.getId();
        this.name = productDetails.getName();
        this.category = productDetails.getCategory();
        this.retail_price = productDetails.getRetail_price();
        this.discounted_price = productDetails.getDiscounted_price();
        this.availability = productDetails.getAvailability();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRetail_price() {
        return this.retail_price;
    }

    public void setRetail_price(Double retailPrice) {
        this.retail_price = retailPrice;
    }

    public Double getDiscounted_price() {
        return this.discounted_price;
    }

    public void setDiscounted_price(Double discountedPrice) {
        this.discounted_price = discountedPrice;
    }

    public Boolean getAvailability() {
        return this.availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

}