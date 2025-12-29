package com.scaler.productservicedec25.exceptions;

public class ProductNotFoundException extends Exception{

    private Long productId;


    public ProductNotFoundException(Long productId){
        super("Product not found with id " + productId);
        this.productId = productId;
    }

    public Long getProductId(){
        return productId;
    }
}
