package main.java.com.uhealth.views.Users;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.controllers.RoutineController;
import main.java.com.uhealth.dao.RoutineDao;
import main.java.com.uhealth.models.Routine;
import main.java.com.uhealth.views.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

public class Food extends JFrame {
    private JPanel foodPanel;
    private JPanel navBar;
    private JLabel welcomeText;
    private JLabel userName;
    private JLabel scheduleField;
    private JLabel scheduleText;
    private JLabel dateField;
    private JLabel dateText;
    private JButton foodButton;
    private JScrollPane scrollPanel;
    private JTable jTableRoutines;

    DefaultTableModel model = new DefaultTableModel();
    RoutineController routineController = new RoutineController();

    private Connection conexion;



    public Food (){
        Connection cn = Conexion.conectar();
        conexion = cn;
        setContentPane(foodPanel);
        setSize(900, 550);
        setResizable(false);
        setTitle("Cliente"+ Login.userName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dateField.setText(User.date);
        scheduleField.setText(User.time);
        this.userName.setText(Login.userName);
        this.userName.setText(Login.userName);

        jTableRoutines = new JTable(model);
        scrollPanel.setViewportView(jTableRoutines);
        executeTableRoutine(); // Funcion que llama el llenado de la tabla




        jTableRoutines.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int row_point = jTableRoutines.rowAtPoint(e.getPoint());
                int column_point = 0;

//                if(row_point > -1){
//                    actaulizar obtener id
//                }
            }
        });

        this.foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //limpia el jframe
                new User().setVisible(true);
            }
        });

    }

    private void executeTableRoutine(){
        // Tabla de las rutinas

        model.addColumn("Id");
        model.addColumn("Fecha");
        model.addColumn("Horario");
        model.addColumn("Comida");
        model.addColumn("Calorias");
        model.addColumn("Carbohidratos");

        //Obtener la rutinas del usuario
        List<Routine> routines = routineController.getRoutinesByUser(Login.userId);

        for (Routine routine : routines){
            Object[] rowData = {routine.getId(), routine.getDate(), routine.getSchedule(), routine.getName(), routine.getCalories(), routine.getCarbs()};
            model.addRow(rowData);
        }


    }
}
