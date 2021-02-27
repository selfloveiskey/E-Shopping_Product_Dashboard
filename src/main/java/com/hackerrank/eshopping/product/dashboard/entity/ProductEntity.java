package com.hackerrank.eshopping.product.dashboard.entity;

import com.hackerrank.eshopping.product.dashboard.model.ProductDetailsRequest;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "products")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = -5602766744641144936L;
    @Id
    @GeneratedValue
    private long productID;
    @Column(
            nullable = false
    )
    private Long id;
    @Column(
            nullable = false,
            length = 50
    )
    private String name;
    @Column(
            nullable = false,
            length = 100
    )
    private String category;
    @Column(
            nullable = false
    )
    private Double retailPrice;
    @Column(
            nullable = false
    )
    private Double discountedPrice;
    @Column(
            nullable = false
    )
    private Boolean availability;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, String category, Double retailPrice, Double discountedPrice, Boolean availability) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.retailPrice = retailPrice;
        this.discountedPrice = discountedPrice;
        this.availability = availability;
    }

    public ProductEntity(ProductDetailsRequest productDetails) {
        this.id = productDetails.getId();
        this.name = productDetails.getName();
        this.category = productDetails.getCategory();
        this.retailPrice = productDetails.getRetailPrice();
        this.discountedPrice = productDetails.getDiscountedPrice();
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

    public Double getRetailPrice() {
        return this.retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getDiscountedPrice() {
        return this.discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Boolean getAvailability() {
        return this.availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

}