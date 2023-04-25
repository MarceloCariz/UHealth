package main.java.com.uhealth.controllers;

import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.service.ProductService;

import java.util.List;

public class ProductController {
    private ProductService productService;

    public ProductController(){
        productService = new ProductService();
    }

    public void addProduct (Product product){
        productService.createProduct(product);
    }

    public List<Product> getProducts(){
        return productService.getProducts();
    }
    public List<Product> getProductsByCategoryId(int categoryId){
        return productService.getProductByCategoryId(categoryId);
    }
}
