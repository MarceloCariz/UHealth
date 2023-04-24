package main.java.com.uhealth.dao;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends ProfileDao{
    private Connection conexion;

    public UserDao(){
        this.conexion = Conexion.conectar();
    }

    public boolean create(User user){
        String query = "INSERT INTO usuarios (nombre, email, password, telefono, idRol, idPerfil) VALUES(?, ? , ? , ? , ?, ?)";
        try{
            //Obterner id perfil
            int idProfile = this.createProfile();
            // Preparar para crear usuario
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, user.getName()); // Inserta el nombre en el 1 ?
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhone());
            statement.setInt(5, user.getIdRol());
            statement.setInt(6, idProfile);
            int result = statement.executeUpdate();

            return result > 0;//result > 0 ? true : false
        }catch (SQLException e){
            System.err.println("Error en la creacion de usuario"+e);
            return false;
        }
    }

    public List<User> getUsers(){
        String query = "SELECT * FROM usuarios";
        List<User> users = new ArrayList<>();

        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("nombre");
                String email = rs.getString("correo");
                String phone = rs.getString("telefono");
                int rolId = rs.getInt("idRol");
                User user = new User(id, name, email, phone, rolId);
                users.add(user);
            }
        }catch (SQLException e){
            System.err.println("Error en la creacion de usuario"+e);
        }

        return users;
    }





    public boolean checkEmail(String email){
        String query = "SELECT COUNT(*) AS existe  from usuarios WHERE email = ?";

        try{
            // Vereficar email
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                int existe = rs.getInt("existe");
                return existe > 0; // Si hay correo marca como true  / false
            }

            return true;
        }catch (SQLException e){
            System.err.println("Error en la creacion de usuario"+e);
            return true;
        }
    }
}
