package Classes.Admin;

import Classes.Database.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsDAO {

    private Connection conexion;

    public ProductsDAO(){
        Connection cn = Conexion.conectar();
        conexion = cn;
    }


    public void getCategories(){
        String query = "SELECT * FROM categorias";
        try{
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<Object[]> categories = new ArrayList<>();

            while (rs.next()){
                Object[] row = new Object[2];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("nombre");
                categories.add(row);
            }

            Products.categories = categories;
            // todo : cerrar conexion


        }catch (SQLException e){
            System.err.println(e);
        }

    }

    public void getProductsByCategory(int categoryId){
        String query = "SELECT * FROM productos WHERE idCategoria = ?";

        try{

            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1,categoryId);
            ResultSet rs = statement.executeQuery();
            List<Object[]> products = new ArrayList<>();

            while (rs.next()){
                Object[] row = new Object[5];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("nombre");
                row[2] = rs.getFloat("calorias");
                row[3] = rs.getFloat("carbohidratos");
                row[4] = rs.getInt("idCategoria");
                products.add(row);
            }

            Products.products = products;
        }catch (SQLException e){
            System.err.println(e);
        }
    }



}
