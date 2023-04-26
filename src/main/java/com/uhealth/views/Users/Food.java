package main.java.com.uhealth.views.Users;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.controllers.RoutineController;
import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.models.Routine;
import main.java.com.uhealth.utils.routineHelpers.DateHelper;
import main.java.com.uhealth.utils.routineHelpers.RoutineHelper;
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
    private JComboBox datesComboBox;

    String allDatesText = "Todas las fechas";
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

        // DEfiniciion de columnas fechas
        jTableRoutines = new JTable(model);
        scrollPanel.setViewportView(jTableRoutines);
        model.addColumn("Id");
        model.addColumn("Fecha");
        model.addColumn("Horario");
        model.addColumn("Comida");
        model.addColumn("Calorias");
        model.addColumn("Carbohidratos");

        //Obtener la rutinas del usuario
        List<Routine> routines = routineController.getRoutinesByUser(Login.userId);
        executeTableRoutine(routines); // Funcion que llama el llenado de la tabla
        //Llenar comboBox con fechas
        List<String> dates = new DateHelper().getDates(routines);
        fillDateComboBox(dates);



        this.datesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Limpiar columnas
                model.setRowCount(0);
                // validar si son todas las fechas
                if(datesComboBox.getSelectedItem().toString().equals(allDatesText)){
                    executeTableRoutine(routines);
                    return;
                }
                String date = datesComboBox.getSelectedItem().toString();
                List<Routine> routinesByDate = new RoutineHelper().getRoutinesByDate(routines, date);
                executeTableRoutine(routinesByDate);
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

    private void executeTableRoutine(List<Routine> routines){
        // Tabla de las rutinas
        for (Routine routine : routines){
            Object[] rowData = {routine.getId(), routine.getDate(), routine.getSchedule(), routine.getName(), routine.getCalories(), routine.getCarbs()};
            model.addRow(rowData);
        }
    }


    private void fillDateComboBox(List<String> dates){
        datesComboBox.addItem(allDatesText);
        for(String date : dates){
            datesComboBox.addItem(date); /// fecha
        }
    }


}
