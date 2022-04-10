package com.technova.product.service;

import com.technova.product.dto.ProductRequest;
import com.technova.product.model.Product;
import com.technova.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    public Product createProduct(ProductRequest productRequest){
        Product newProduct = new Product();
        newProduct.setName(productRequest.getName());
        newProduct.setCategory(productRequest.getCategory());
        newProduct.setUnitPrice(productRequest.getUnitPrice());
        newProduct.setQuantityAvailable(productRequest.getQuantityAvailable());
        return repo.save(newProduct);
    }

    public Optional<Product> getByProductName(String name) {
        Optional<Product> product = repo.findByName(name);
        return product;
    }

    public Optional<Product> getByProductId(Long id) {
        return repo.findById(id);
    }

    public Product updateProduct(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setUnitPrice(productRequest.getUnitPrice());
        product.setQuantityAvailable(productRequest.getQuantityAvailable());
        return repo.save(product);
    }

    public void deleteProduct(Product product) {
        repo.delete(product);
    }

    public List<Product> getAll() {
       return repo.findAll();
    }
}
