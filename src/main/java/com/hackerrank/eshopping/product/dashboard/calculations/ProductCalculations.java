package com.hackerrank.eshopping.product.dashboard.calculations;

public class ProductCalculations {
/*
 |------------------------------------------------------------------------------
 | Discount Percentage = (Retail Price - Discounted Price) / Retail Price X 100
 |------------------------------------------------------------------------------
 */
    Integer discountPercentage;
    Double percentage;
    int oneHundred = 100;

     public Integer calculateDiscountPercentage(Double retail_price, Double discounted_price){
         percentage = (retail_price - discounted_price) / retail_price * oneHundred;
         discountPercentage = percentage.intValue();

         return discountPercentage;
     }
}