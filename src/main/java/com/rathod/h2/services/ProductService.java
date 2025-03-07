package com.rathod.h2.services;

import com.rathod.h2.entities.Product;

import java.util.List;

/**
 * @author Chirag
 */
public interface ProductService {
    Product createProduct(Product product);

    Product updateProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(long productId);

    void deleteProduct(long productId);
}
