package com.hackerrank.eshopping.product.dashboard.model;

public class ProductDetailsRequest {

    private Long id;
    private String name;
    private String category;
    private Double retailPrice;
    private Double discountedPrice;
    private Boolean availability;

    public ProductDetailsRequest() {
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