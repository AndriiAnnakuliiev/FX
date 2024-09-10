package com.example.computerstore.controllers;

import com.example.computerstore.models.Category;

import com.example.computerstore.models.Product;

import com.example.computerstore.repository.CategoryRepository;
import com.example.computerstore.repository.GraphicsCardRepository;
import com.example.computerstore.repository.MotherboardRepository;
import com.example.computerstore.repository.PowerSupplyRepository;
import com.example.computerstore.repository.ProcessorRepository;
import com.example.computerstore.repository.ProductRepository;
import com.example.computerstore.repository.RAMRepository;
import com.example.computerstore.repository.SSDRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SSDRepository ssdRepository;

    @Autowired
    private RAMRepository ramRepository;

    @Autowired
    private GraphicsCardRepository graphicsCardRepository;

    @Autowired
    private MotherboardRepository motherboardRepository;

    @Autowired
    private PowerSupplyRepository powerSupplyRepository;

    @Autowired
    private ProcessorRepository processorRepository;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping
public ResponseEntity<List<Object>> getProductsByCategory(@RequestParam("categoryId") List<Integer> categoryIds) {
  
    List<Object> products = new ArrayList<>();

    for (Integer categoryId : categoryIds) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);

        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            String categoryName = category.getName().toLowerCase();

            switch (categoryName) {
                case "ssds":
                    products.addAll(ssdRepository.findAll());  
                    break;
                case "rams":
                    products.addAll(ramRepository.findAll());  
                    break;
                case "graphicscards":
                    products.addAll(graphicsCardRepository.findAll());  
                    break;
                case "motherboards":
                    products.addAll(motherboardRepository.findAll());  
                    break;
                case "powersupplies":
                    products.addAll(powerSupplyRepository.findAll()); 
                    break;
                case "processors":
                    products.addAll(processorRepository.findAll()); 
                    break;
                default:
                    return ResponseEntity.notFound().build();
            }
        }
    }

    return ResponseEntity.ok(products);
}



    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setName(productDetails.getName());
            updatedProduct.setDescription(productDetails.getDescription());
            updatedProduct.setPrice(productDetails.getPrice());
            updatedProduct.setStockQuantity(productDetails.getStockQuantity());
            return ResponseEntity.ok(productRepository.save(updatedProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
