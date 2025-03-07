package com.rathod.h2.utils;

import com.rathod.h2.entities.Product;
import com.rathod.h2.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Chirag
 */
@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final ProductService productService;

    DataLoader(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Method to run on application startup. Loads initial data into the database.
     *
     * @param args Arguments passed to the application
     * @throws Exception If there is an error while loading the data
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("Loading data...");

        // Create products
        List<Product> products = Arrays.asList(
                // Product 1
                createProduct("Product 1", "Description for Product 1", BigDecimal.valueOf(50.00)),
                // Product 2
                createProduct("Product 2", "Description for Product 2", BigDecimal.valueOf(150.00))
        );

        // Save products
        log.info("Saving products...");
        products.forEach(productService::createProduct);

        log.info("Data loaded successfully");
        log.info("Loaded products: {}", productService.getAllProducts());
    }

    /**
     * Creates a new Product instance with the specified name, description, and price.
     *
     * @param name        The name of the product.
     * @param description A brief description of the product.
     * @param price       The price of the product.
     * @return A new Product instance populated with the provided details.
     */
    private Product createProduct(String name, String description, BigDecimal price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        return product;
    }

}