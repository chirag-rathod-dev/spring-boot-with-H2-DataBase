package com.rathod.h2.services;

import com.rathod.h2.entities.Product;
import com.rathod.h2.exceptions.ProductNotFoundException;
import com.rathod.h2.exceptions.ResourceNotFoundException;
import com.rathod.h2.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Chirag
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.findById(product.getId())
                .map(p -> {
                    p.setName(product.getName());
                    p.setDescription(product.getDescription());
                    return productRepository.save(p);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id : " + product.getId()));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        productRepository::delete,
                        () -> {
                            throw new ProductNotFoundException("Product not found with id: " + id);
                        }
                );
    }

}
