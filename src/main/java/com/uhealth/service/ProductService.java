package main.java.com.uhealth.service;

import main.java.com.uhealth.dao.ProductDao;
import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.models.Profile;

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

    public void deleteProduct(int idProduct){
        if(productDao.deleteProduct(idProduct)){
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
            return;
        }
        JOptionPane.showMessageDialog(null, "Hubo un error en la eliminacion del producto");
    }

    public List<Product> getProducts(){
        return productDao.getProducts();
    }

    public List<Product> getProductByCategoryId(int categoryId){
        return productDao.getProductsByCategoryId(categoryId);
    }

}
