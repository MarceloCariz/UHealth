package main.java.com.uhealth.dao;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.models.Routine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoutineDao {
    private Connection conexion;

    public RoutineDao(){
        conexion = Conexion.conectar();
    }

    public boolean create(Routine routine){
        String query = "INSERT INTO rutinas (fecha, horario, idProducto, idUsuario) VALUES(?,?,?,?)";
        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1,routine.getDate()); // Inserta la fecha en el primer ?
            statement.setString(2, routine.getSchedule());
            statement.setInt(3, routine.getIdProduct());
            statement.setInt(4, routine.getIdUser());

            int result = statement.executeUpdate(); // filas afectadas

            return result > 0; //result > 0 ? true : false
        }catch (SQLException e){
            System.err.println("Error en la creacion de la rutina"+e);
            return false;
        }
    }
}
