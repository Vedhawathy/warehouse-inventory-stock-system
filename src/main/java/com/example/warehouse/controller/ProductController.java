package com.example.warehouse.controller;

import com.example.warehouse.entity.Product;
import com.example.warehouse.service.ProductService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Add product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    // Get all products
    @GetMapping
    public List<Product> getProducts() {
        return service.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getProductById(id);
    }

    // Update product
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return service.updateProduct(id, product);
    }

    // Delete product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        service.deleteProduct(id);

        return "Product deleted successfully";
    }

    // Get low-stock products
    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts() {

        return service.getLowStockProducts();
    }
}
