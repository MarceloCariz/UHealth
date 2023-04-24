package main.java.com.uhealth.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    private static List<Connection> connections = new ArrayList<>();
    ///Conexion local
    public static Connection conectar(){
        try{
            Connection cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/uhealth?useSSL=false&requireSSL=false&verifyServerCertificate=false",
                    "root",
                    "password");
            connections.add(cn);
            return  cn;
        }catch (SQLException e){
            System.out.println("Error en la conexion local"+ e);
        }
        return (null);
    }

    public static void closeAllConnections() throws SQLException {
        for (Connection connection : connections) {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
        connections.clear();
    }
}
