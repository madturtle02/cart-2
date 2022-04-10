package com.technova.product.controller;

import com.technova.product.dto.ProductRequest;
import com.technova.product.model.Product;
import com.technova.product.service.ProductService;
import com.technova.product.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody @Valid ProductRequest productRequest){
        Optional<Product> product = productService.getByProductName(productRequest.getName());
        if(product.isPresent()){
            log.info("Product with id {} is fetched.",product.get().getId());
            return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"Product with same product name already exists.",product,null);
        }
        Product savedProduct = productService.createProduct(productRequest);
        return ApiResponse.generateResponse(HttpStatus.OK.value(),"Product created susscessfully.",savedProduct,null);
    }
    @GetMapping("/products")
    public ResponseEntity getAll(){
        List<Product> product= productService.getAll();
        if(product.size()>0){
            log.info("Products are fetched.",product);
            return ApiResponse.generateResponse(HttpStatus.OK.value(),"Product fetched successfully", product,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"Products not found", null,"Products not found.");
    }

    @GetMapping("/products/name")
    public ResponseEntity getByProductName(@RequestParam(name="name",required = true) String name){
        Optional<Product> existingProduct= productService.getByProductName(name);
        if(existingProduct.isPresent()){
            log.info("Product with id {} is fetched.",existingProduct.get().getId());
            return ApiResponse.generateResponse(HttpStatus.OK.value(),"Product fetched successfully", existingProduct,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"Product with name "+name+" not found", null,"Product not found.");
    }

    @GetMapping("/products/id")
    public ResponseEntity getByProductId(@RequestParam(name="id",required = true) Long id){
        Optional<Product> existingProduct= productService.getByProductId(id);
        if(existingProduct.isPresent()){
            log.info("Product with id {} is fetched.",existingProduct.get().getId());
            return ApiResponse.generateResponse(HttpStatus.OK.value(),"Product fetched successfully", existingProduct,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"Product with id "+id+" not found", null,"Product not found.");
    }


    @PutMapping("/products/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest){
        Optional<Product> existingProduct = productService.getByProductId(id);
        if(existingProduct.isPresent()){
            log.info("Product with id {} is fetched.",existingProduct.get().getId());
            productService.updateProduct(existingProduct.get(),productRequest);
            return ApiResponse.generateResponse(HttpStatus.OK.value(),"Product updated successfully", existingProduct,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"Product with id "+id+" not found", null,"Product not found.");
    }

    @DeleteMapping ("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Optional<Product> existingProduct = productService.getByProductId(id);
        if(existingProduct.isPresent()){
            log.info("Product with id {} is fetched.",existingProduct.get().getId());
            productService.deleteProduct(existingProduct.get());
            return ApiResponse.generateResponse(HttpStatus.OK.value(),"Product deleted successfully", null,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(),"Product with id "+id+" not found", null,"Product not found.");

    }

}
