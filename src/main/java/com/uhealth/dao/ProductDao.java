package main.java.com.uhealth.dao;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection conexion;

    public ProductDao(){
        this.conexion = Conexion.conectar();
    }

    public boolean create(Product product){
        String query = "INSERT INTO productos (nombre, calorias, carbohidratos, idCategoria) VALUES(?,?,?,?)";
        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, product.getName()); // Insertar el nombre
            statement.setFloat(2, product.getCalories());
            statement.setFloat(3, product.getCarbs());
            statement.setInt(4,product.getCategoryId());

            int result = statement.executeUpdate();
            return result > 0;
        }catch (SQLException e){
            System.err.println(e);
            System.err.println("Error en la creacion de producto"+e);
            return false;
        }
    }

    public boolean updateProduct(Product product){
        String query = "UPDATE productos SET nombre = ? , calorias = ? , carbohidratos = ? WHERE id = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getCalories());
            statement.setFloat(3, product.getCarbs());
            statement.setInt(4, product.getId());

            int result = statement.executeUpdate();
            return result > 0;
        }catch (SQLException e){
            System.err.println("Error en la actualizacion de producto"+e);
            return false;
        }
    }

    public boolean deleteProduct(int idProduct){
        String query = "DELETE  FROM productos WHERE id = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idProduct);
            int result = statement.executeUpdate();
            return result > 0;
        }catch (SQLException e){
            System.err.println("Error en la elimincacion de producto"+e);
            return false;
        }
    }



    public List<Product> getProducts(){
        String query = "SELECT p.*, c.nombre categoria  from productos p JOIN categorias c ON p.idCategoria = c.id";
        List<Product> products = new ArrayList<>();

        try{
            PreparedStatement statement = conexion.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("nombre");
                float calories = rs.getFloat("calorias");
                float carbs = rs.getFloat("carbohidratos");
                String category = rs.getString("categoria");
                Product product = new Product(id, name, calories, carbs, category);
                products.add(product);
            }

        }catch (SQLException e){
            System.err.println("Error en la obtencion de los productos"+e);
        }
        return  products;
    }

    public List<Product> getProductsByCategoryId(int categoryId){
        String query = "SELECT * FROM productos WHERE idCategoria = ?";
        List<Product> products = new ArrayList<>();

        try{

            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1,categoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("nombre");
                float calories = rs.getFloat("calorias");


                Product product = new Product(id, name, calories);
                products.add(product);
            }

        }catch (SQLException e){
            System.err.println(e);
            System.err.println("Error en la obtencion de productos por categoria"+e);

        }

        return products;
    }
}
