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
        conexion = Conexion.conectar();
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
