package Classes.Admin;

import Classes.Database.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsDAO {
    Products products = new Products();

    private Connection conexion;

//    Products products = new Products();

    public ProductsDAO(){
        Connection cn = Conexion.conectar();
        conexion = cn;
    }


    public void createProduct(Products product){
        String query = "INSERT INTO productos (nombre, calorias, carbohidratos, idCategoria) VALUES(?,?,?,?)";
        try{
            PreparedStatement  stmt = conexion.prepareStatement(query);
            stmt.setString(1, product.getName()); // Insertar el nombre
            stmt.setFloat(2, product.getCalories());
            stmt.setFloat(3, product.getCarbs());
            stmt.setInt(4,product.getCategoryId());

            int rowsAffected = stmt.executeUpdate();

            if(rowsAffected <= 0){
                System.err.println("Hubo un error");
                return;
            }
            System.out.println("Comida creada exitosamente");
        }catch (SQLException e){
            System.err.println(e);
        }
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



    public List<Products> getProductsByCategory(int categoryId){
        System.out.println(categoryId);
        String query = "SELECT * FROM productos WHERE idCategoria = ?";
        List<Products> products = new ArrayList<>();

        try{

            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1,categoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                Products product = new Products();
                product.setName(rs.getString("nombre"));
                product.setCalories(rs.getFloat("calorias"));
                product.setId(rs.getInt("id"));
//                row[0] = rs.getInt("id");
//                row[1] = rs.getString("nombre");
//                row[2] = rs.getFloat("calorias");
//                row[3] = rs.getFloat("carbohidratos");
//                row[4] = rs.getInt("idCategoria");
//                products.add(row);
                products.add(product);
            }

            Products.products = products;
        }catch (SQLException e){
            System.err.println(e);
        }

        return products;
    }

    public float getCaloriesByProduct(String productName){
        Optional<Products> productFilter= Products.products.stream()
                .filter(product -> product.getName().equals(productName))
               // .map(Products::getCalories) // transforma el objeto Product en un float con el valor de price
                .findFirst();
        if(productFilter.isPresent()){
            Products productsInfo = productFilter.get();
            float calories = productsInfo.getCalories();
//            products.setId(productsInfo.getId());
            Routines.idProduct = productsInfo.getId();

            return calories;
        }else{
            products.setCalories(0.1f);
            return 0;
        }

    }





}
