package Classes.Admin;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.views.Login;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoutinesDAO {
    Routines routines = new Routines();

    private Connection conexion;

    // Conexion de base de datos
    public RoutinesDAO(){
        Connection cn = Conexion.conectar();
        conexion = cn;
    }


    /// Crear rutina
    public void createRoutine(Routines routine){
        String query = "INSERT INTO rutinas (fecha, horario, idProducto, idUsuario) VALUES(?,?,?,?)";
        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1,routine.getDate()); // Inserta la fecha en el primer ?
            statement.setString(2, routine.getSchedule());
            statement.setInt(3, routine.getIdProduct());
            statement.setInt(4, routine.getIdUser());

            int rowsAffected = statement.executeUpdate(); // filas afectadas

            // Evalua si hubieron cambios
            if(rowsAffected <= 0){
                System.err.println("Hubo un error");
                conexion.close();
                return;
            }
        }catch (SQLException e){
            System.err.println("Error en la creacion de la rutina"+e);
        }
    }


        //Obtener rutinas segun el id del usuario
    public void  getRoutinesByUserId(DefaultTableModel model){
        String query = "SELECT r.id ,r.fecha , r.horario , p.nombre, p.calorias, p.carbohidratos from rutinas r JOIN productos p ON r.idProducto = p.id JOIN usuarios u ON u.id_usuario = r.idUsuario WHERE u.id_usuario = ?"
                + " " + "ORDER BY r.id desc";

        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, Login.userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                Object[] row = new Object[6];
                for (int i = 0; i < 6; i++){
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }


            conexion.close();

        }catch (SQLException e){
            System.err.println("Error en la obtencion de las rutinas"+e);
        }
    }

}
