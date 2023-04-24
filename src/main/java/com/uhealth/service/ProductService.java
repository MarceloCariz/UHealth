package main.java.com.uhealth.service;

import main.java.com.uhealth.dao.ProductDao;
import main.java.com.uhealth.models.Product;

import javax.swing.*;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(){productDao = new ProductDao();}

    public void createProduct(Product product){
        if(productDao.create(product)){
            JOptionPane.showMessageDialog(null, "Producto creado correctamente");
            return;
        }
        JOptionPane.showMessageDialog(null, "Hubo un error en la creacion del producto");
    }

    public List<Product> getProductByCategoryId(int categoryId){
        return productDao.getProductsByCategoryId(categoryId);
    }
}
