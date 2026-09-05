package com.example.warehouse.service;

import com.example.warehouse.entity.Product;
import com.example.warehouse.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Add product
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Update product
    public Product updateProduct(Long id, Product data) {

        return repository.findById(id).map(product -> {

            product.setName(data.getName());
            product.setCategory(data.getCategory());
            product.setPrice(data.getPrice());
            product.setQuantity(data.getQuantity());
            product.setMinimumStock(data.getMinimumStock());

            return repository.save(product);

        }).orElse(null);
    }

    // Delete product
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    // Low stock products
    public List<Product> getLowStockProducts() {

        return repository.findAll()
                .stream()
                .filter(product ->
                        product.getQuantity() <= product.getMinimumStock())
                .toList();
    }
}
