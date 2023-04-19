package Classes.Admin;

import Classes.Database.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private Connection conexion;

    public  UserDAO(){
        Connection cn = Conexion.conectar();
        conexion = cn;
    }

    public void createUser(Users usuario){
        String query = "INSERT INTO usuarios (nombre, email, password, telefono, idRol) VALUES(?, ? , ? , ? , ?)";
        try{
            // Todo: Comprobar correo
            // Preparar para crear usuario
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, usuario.getName());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getPhone());
            statement.setInt(5, usuario.getIdRol());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected <= 0){
                System.err.println("Hubo un error");
                return;
            }
            System.out.println("Usuario creado exitosamente");
        }catch (SQLException e){
            System.err.println("Error en la creacion de usuario"+e);
        }
    }




}
