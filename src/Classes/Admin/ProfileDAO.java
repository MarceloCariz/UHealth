package Classes.Admin;

import Classes.Database.Conexion;

import java.sql.Connection;

public class ProfileDAO {

    private Connection conexion;


    public ProfileDAO(){
        Connection cn = Conexion.conectar();
        conexion = cn;
    }



    public void updateProfile(){
        String query = "UPDATE usuarios SET  WHERE";
    }
}