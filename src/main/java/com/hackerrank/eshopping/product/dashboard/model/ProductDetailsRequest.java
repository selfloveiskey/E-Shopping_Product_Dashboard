package com.hackerrank.eshopping.product.dashboard.model;

/*
|--------------------------------------------------------
| Incoming JSON payload body
|--------------------------------------------------------
*/
public class ProductDetailsRequest {

    private Long id;
    private String name;
    private String category;
    private Double retail_price;
    private Double discounted_price;
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

    public Double getRetail_price() {
        return this.retail_price;
    }

    public void setRetail_price(Double retail_price) {
        this.retail_price = retail_price;
    }

    public Double getDiscounted_price() {
        return this.discounted_price;
    }

    public void setDiscounted_price(Double discounted_price) {
        this.discounted_price = discounted_price;
    }

    public Boolean getAvailability() {
        return this.availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}