package main.java.com.uhealth.service;

import main.java.com.uhealth.dao.ProductDao;
import main.java.com.uhealth.models.Product;

import javax.swing.*;

public class ProductService {
    private ProductDao productDao;

    public ProductService(){productDao = new ProductDao();}

    public void createProduct(Product product){
        productDao.create(product);
        JOptionPane.showMessageDialog(null, "Producto creado correcto");
    }
}
