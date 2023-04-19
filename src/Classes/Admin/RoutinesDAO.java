package Classes.Admin;

import Classes.Database.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoutinesDAO {
    Routines routines = new Routines();

    private Connection conexion;

//    Products products = new Products();

    public RoutinesDAO(){
        Connection cn = Conexion.conectar();
        conexion = cn;
    }


    public void createRoutine(Routines routine){
        String query = "INSERT INTO rutinas (fecha, horario, idProducto, idUsuario) VALUES(?,?,?,?)";
        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1,routine.getDate());
            statement.setString(2, routine.getSchedule());
            statement.setInt(3, routine.getIdProduct());
            statement.setInt(4, routine.getIdUser());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected <= 0){
                System.err.println("Hubo un error");
                return;
            }
            System.out.println("Rutina creada exitosamente");
        }catch (SQLException e){
            System.err.println("Error en la creacion de la rutina"+e);
        }
    }

}
