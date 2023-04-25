package main.java.com.uhealth.views.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.com.uhealth.controllers.UserController;
import main.java.com.uhealth.models.User;
import main.java.com.uhealth.views.Login;

public class Admin extends JFrame{
    private JPanel panel1;
    private JLabel userName;
    private JButton addFoodButton;
    private JPanel createUserPanel;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel nameLabel;
    private JTextField passField;
    private JLabel passLabel;
    private JButton createButton;
    private JPanel navBarPanel;
    private JLabel titleLabel;
    private JComboBox rolcomboBox;
    private JLabel rolLabel;
    private JButton foodManagerButton;
    private JButton manageUsersButton;


    public Admin(){
        setContentPane(panel1);
        setSize(900, 550);
        setResizable(false);
        setTitle("Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);


        //TraerDatos

        // Boton para crear usuarios cuando se haga click
        this.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String password = passField.getText();
                // Validar campos vacios
                boolean isValid = validateFields();
                if(!isValid) return;

                //Validar email
                boolean isValidEmail = validateEmail(email);
                if(!isValidEmail){
                    JOptionPane.showMessageDialog(null, "El email no es valido");
                    return;
                }

                //Validar telefono
                boolean isValidPhone = isValidPhone(phone);
                if(!isValidPhone) return;

                int rolId  = selectIdRol();
                User user = new User(name, email, password, phone, rolId);
                UserController userController = new UserController();
                userController.addUser(user);

                nameField.setText("");
                emailField.setText("");
                phoneField.setText("");
                passField.setText("");
                rolcomboBox.setSelectedIndex(0);


            }
        });

        // Ir ha agregar comida
        this.addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FoodAdmin().setVisible(true);
            }
        });

        this.foodManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FoodManager().setVisible(true);
            }
        });

        this.manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UserManager().setVisible(true);
            }
        });
    }

    private int selectIdRol(){
        String rolValue = rolcomboBox.getSelectedItem().toString();
        int idRol = 0;
        switch (rolValue){
            case "administrador":
                idRol = 1;
                break;
            case "usuario":
                idRol = 2;
                break;
            default:
                System.out.println("Rol desconocido: " + rolValue);
                break;
        }
        return  idRol;
    }
    private boolean validateFields(){
        if(nameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty() || passField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor llenar todos los campos");
            return  false;
        }

        return  true;
    }

    private boolean validateEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhone(String phone){
        if(!phone.matches("\\d+") || (phone.length() == 9) == false){ //que significa "uno o más dígitos numéricos".
            JOptionPane.showMessageDialog(null, "Formato de numero incorrecto: Revise que sean numeros y no mayor o menor a 9 digitos");
            return false;
        }
        return true;
    }
}
