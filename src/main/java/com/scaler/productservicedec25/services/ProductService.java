package com.scaler.productservicedec25.services;

import com.scaler.productservicedec25.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);

    List<Product> getAllProducts();
}
