package main.java.com.uhealth.views.Admin;

import main.java.com.uhealth.controllers.UserController;
import main.java.com.uhealth.models.User;
import main.java.com.uhealth.views.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserManager extends JFrame{
    private JPanel navBarPanel;
    private JLabel titleLabel;
    private JLabel userName;
    private JButton foodManagerButton;
    private JButton addFoodButton;
    private JButton createUserButton;
    private JTable jTableUsers;
    private JPanel mainPanel;
    private JScrollPane scrollPanel;

    DefaultTableModel model = new DefaultTableModel();
    UserController userController = new UserController();

    public UserManager(){
        setContentPane(mainPanel);
        setSize(900, 550);
        setResizable(false);
        setTitle("Administrador-Administrar usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);
        jTableUsers = new JTable(model);
        scrollPanel.setViewportView(jTableUsers);
        //Definit columnas
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Telefono");
        model.addColumn("Email - Correo");
        model.addColumn("Rol");
        // LLenar tabla
        executeTableUser();


        this.addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FoodAdmin().setVisible(true);
            }
        });

        this.createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Admin().setVisible(true);
            }
        });

        this.foodManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FoodManager().setVisible(true);
            }
        });
    }


    private void executeTableUser(){
        List<User> users = userController.getUsers();

        for (User user : users){
            String rolName = user.getIdRol() == 1 ? "Administrador" : "Cliente";
            Object[] rowData = {user.getId(), user.getName(), user.getPhone(), user.getEmail(), rolName};
            model.addRow(rowData);
        }

    }
}
