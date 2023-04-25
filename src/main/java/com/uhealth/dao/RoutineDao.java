package main.java.com.uhealth.dao;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.models.Routine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoutineDao {
    private Connection conexion;

    public RoutineDao(){
        this.conexion = Conexion.conectar();
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

    public List<Routine> get(int userId){
        String query = "SELECT r.id, r.fecha, r.horario, p.nombre, p.calorias, p.carbohidratos "
                + "FROM rutinas r "
                + "JOIN productos p ON r.idProducto = p.id "
                + "JOIN usuarios u ON u.id_usuario = r.idUsuario "
                + "WHERE u.id_usuario = ? "
                + "ORDER BY r.id DESC";

        List<Routine> routines = new ArrayList<>();
        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1,userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String date = rs.getString("fecha");
                String time = rs.getString("horario");
                String name = rs.getString("nombre");
                float calories = rs.getFloat("calorias");
                float carbs = rs.getFloat("carbohidratos");

                Routine routine =  new Routine(id, date, time, name, calories, carbs);
                routines.add(routine);
            }
        }catch (SQLException e){
            System.err.println("Error en la obtencion de las rutinas"+e);
        }

        return  routines;
    }
}
