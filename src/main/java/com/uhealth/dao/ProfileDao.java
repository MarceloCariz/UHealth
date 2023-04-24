package main.java.com.uhealth.dao;

import main.java.com.uhealth.Database.Conexion;

import java.sql.*;

public class ProfileDao {

    private Connection conexion;

    public ProfileDao(){
        conexion = Conexion.conectar();
    }

    public int createProfile(){
        String query = "INSERT INTO perfiles (id) VALUES(NULL)";
        int idProfile = 0;
        try{
            PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                idProfile = rs.getInt(1);
            }

        }catch (SQLException e){
            System.err.println("Error al crear el perfil" + e);
        }

        return idProfile;
    }

}
