package Views.Users;

import Classes.Admin.Routines;
import Views.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends JFrame {


    private JPanel profileMainPanel;
    private JPanel navBar;
    private JLabel welcomeText;
    private JLabel userName;
    private JLabel scheduleField;
    private JLabel scheduleText;
    private JLabel dateField;
    private JLabel dateText;
    private JButton routinesButton;
    private JTextField ageField;
    private JTextField weightField;
    private JTextField heightField;
    private JLabel titleLabel;
    private JLabel ageLabel;
    private JLabel weightLabel;
    private JLabel heightLabel;
    private JLabel imcLabel;
    private JLabel imcField;
    private JButton saveProfileButton;
    private JButton addFoodButton;

    public Profile(){
        setContentPane(profileMainPanel);
        setSize(600, 550);
        setResizable(false);
        setTitle("Perfil - "+ Login.userName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);

        this.addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new User().setVisible(true);
            }
        });

        this.routinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Food().setVisible(true);
            }
        });
    }
}
