package main.java.com.uhealth.views.Admin;

import main.java.com.uhealth.views.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManager extends JFrame{
    private JPanel navBarPanel;
    private JLabel titleLabel;
    private JLabel userName;
    private JButton foodManagerButton;
    private JButton addFoodButton;
    private JButton createUserButton;
    private JPanel userTable;
    private JTable jTableUsers;
    private JPanel mainPanel;

    public UserManager(){
        setContentPane(mainPanel);
        setSize(900, 550);
        setResizable(false);
        setTitle("Administrador-Administrar usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);



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
}
