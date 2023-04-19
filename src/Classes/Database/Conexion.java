package Classes.Database;
import java.sql.*;
public class Conexion {
    ///Conexion local
    public static Connection conectar(){
        try{
            Connection cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/uhealth?useSSL=false&requireSSL=false&verifyServerCertificate=false",
                    "root",
                    "password");

            return  cn;
        }catch (SQLException e){
            System.out.println("Error en la conexion local"+ e);
        }
        return (null);
    }
}
