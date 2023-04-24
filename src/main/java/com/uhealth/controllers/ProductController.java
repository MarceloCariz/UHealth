package main.java.com.uhealth.controllers;

import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.service.ProductService;

public class ProductController {
    private ProductService productService;

    public ProductController(){
        productService = new ProductService();
    }

    public void addProduct (Product product){
        productService.createProduct(product);
    }
}
