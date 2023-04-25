package main.java.com.uhealth.views.Admin;

import main.java.com.uhealth.controllers.UserController;
import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.models.User;
import main.java.com.uhealth.utils.validations.Validations;
import main.java.com.uhealth.views.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserManager extends JFrame  {
    private JPanel navBarPanel;
    private JLabel titleLabel;
    private JLabel userName;
    private JButton foodManagerButton;
    private JButton addFoodButton;
    private JButton createUserButton;
    private JTable jTableUsers;
    private JPanel mainPanel;
    private JScrollPane scrollPanel;
    private JPanel actionsPanel;
    private JButton deleteUserButton;
    private JButton updateUserButton;

    DefaultTableModel model = new DefaultTableModel();
    UserController userController = new UserController();

    Validations validations =  new Validations();

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


        this.updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });
        this.deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

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

    private void updateUser(){
        int selectedFood = jTableUsers.getSelectedRow();

        if(selectedFood == -1) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
            return;
        };
        int id = (int) model.getValueAt(selectedFood, 0); // 0 = id
        String name = (String) model.getValueAt(selectedFood, 1); // 1 = nombre
        String phone = (String) model.getValueAt(selectedFood, 2); // telefono
        String email = (String) model.getValueAt(selectedFood, 3); // correo

        boolean isValidPhone = validations.isValidPhone(phone);
        if(!isValidPhone) return;

        boolean isValidEmail = validations.validateEmail(email);
        if(!isValidEmail) return;


        User user = new User(id, name, phone, email);

        userController.updateUser(user);
        model.setRowCount(0);
        executeTableUser();

    }

    private void deleteUser(){
        int selectedUser = jTableUsers.getSelectedRow();
        if(selectedUser == -1) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
            return;
        };
        int userId = (int) model.getValueAt(selectedUser, 0);
        int result = JOptionPane.showConfirmDialog(null, "Aceptar para eliminar el usuario con el id: "+  userId , "Aceptar",JOptionPane.YES_NO_OPTION);

        if(result == JOptionPane.YES_OPTION){
            userController.deleteUser(userId);
            model.setRowCount(0);
            executeTableUser();
        }
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
