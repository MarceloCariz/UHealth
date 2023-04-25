package main.java.com.uhealth.dao;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.models.Profile;
import main.java.com.uhealth.dao.UserDao;
import main.java.com.uhealth.models.User;

import java.sql.*;

public class ProfileDao {

    private Connection conexion;
    private UserDao userDao;

    public ProfileDao(){
        this.conexion = Conexion.conectar();
        this.userDao = new UserDao();
    }



    public boolean updateProfile(Profile profile){
        String query = "UPDATE perfiles " +
                "SET edad = ?, " +
                "peso = ?, " +
                "altura = ?, " +
                "imc = ?, " +
                "sexo = ? " +
                "WHERE id = ?";

        try{
            // Obtener idprofile por el userDao

            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1,profile.getAge());
            statement.setFloat(2, profile.getWeight());
            statement.setFloat(3, profile.getHeight());
            statement.setFloat(4, profile.getImc());
            statement.setString(5, String.valueOf(profile.getGender()));
            statement.setInt(6, profile.getId());

            int rowsUpdated = statement.executeUpdate(); // devuelve un int

//            conexion.close();

            return  rowsUpdated > 0;

        }catch (SQLException e){
            System.err.println("Error al actualizar perfil" + e);
            return false;
        }
    }


    public int createProfile(){
        String query = "INSERT INTO perfiles (edad, peso, altura, imc, sexo) VALUES(0, 0, 0, 0, 'U')"; // U undefined
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


    public Profile getProfile(int userId){
        String query = "SELECT p.* FROM perfiles p join usuarios u ON u.idPerfil = p.id WHERE u.id_usuario = ?";
        Profile profile = null;
        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                profile = new Profile();
                profile.setId(rs.getInt("id"));
                profile.setAge(rs.getInt("edad"));
                profile.setWeight(rs.getFloat("peso"));
                profile.setHeight(rs.getFloat("altura"));
                profile.setImc(rs.getFloat("imc"));
                String genderString = rs.getString("sexo");
                char gender = genderString.charAt(0);
                profile.setGender(gender);
            }

        }catch (SQLException e){
            System.err.println(e);
        }

        return  profile;
    }

}
