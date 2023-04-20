package Views.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Admin.Users;
import Classes.Admin.UserDAO;
import Views.Login;

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

    UserDAO userDAO = new UserDAO();
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
                String rolValue = rolcomboBox.getSelectedItem().toString();
                System.out.println(rolValue);
                int idRol = 0;
                switch (rolValue){
                    case "administrador":
                        idRol = 1;
                        break;
                    case "cliente":
                        idRol = 2;
                        break;
                    case  "maestro":
                        idRol = 3;
                        break;
                    default:
                        System.out.println("Rol desconocido: " + rolValue);
                        break;
                }

                Users user = new Users(name, email, phone, password, idRol);
                userDAO.createUser(user);
                JOptionPane.showMessageDialog(null, "Usuario creado Correctamente");
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

    }
}
