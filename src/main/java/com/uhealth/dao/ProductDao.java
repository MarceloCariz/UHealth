package main.java.com.uhealth.dao;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
