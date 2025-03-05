package com.rathod.h2.utils;

import com.rathod.h2.entities.Product;
import com.rathod.h2.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data...");

        Product product1 = createProduct("Product 1", "Description for Product 1", BigDecimal.valueOf(50.00));
        Product product2 = createProduct("Product 2", "Description for Product 2", BigDecimal.valueOf(150.00));

        productService.createProduct(product1);
        productService.createProduct(product2);

        log.debug("Data loaded successfully");
        log.info("Data loaded successfully");

        System.out.println("-----------------------------");
        System.out.println(productService.getAllProduct());
    }

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