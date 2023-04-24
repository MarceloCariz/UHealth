package main.java.com.uhealth.dao;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.models.Category;
import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private Connection conexion;

    public CategoryDao(){
        conexion = Conexion.conectar();
    }


    public List<Category> get(){
        String query = "SELECT * FROM categorias";
        List<Category> categories = new ArrayList<>();

        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("nombre");
                Category category = new Category(id, name);
                categories.add(category);
            }
            conexion.close();
        }catch (SQLException e){
            System.err.println("Error en la obtencion de categorias"+e);
        }

        return  categories;
    }
}
