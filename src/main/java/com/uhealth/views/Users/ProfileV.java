package main.java.com.uhealth.views.Users;

import main.java.com.uhealth.controllers.ProfileController;
import main.java.com.uhealth.models.Profile;
import main.java.com.uhealth.views.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileV extends JFrame {


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
    private JPanel profileForm;

    public ProfileV(){
        setContentPane(profileMainPanel);
        setSize(600, 550);
        setResizable(false);
        setTitle("Perfil - "+ Login.userName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);


        this.saveProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int age = Integer.parseInt(ageField.getText());
                float weight = Float.parseFloat(weightField.getText());
                float height = Float.parseFloat(heightField.getText());

                float imc  = weight / (height * height);// peso / altura * altura
                imcField.setText(Float.toString(imc));
                ProfileController profileController =  new ProfileController();

                Profile profileInfo = profileController.getProfile(Login.userId);

                Profile profile = new Profile(profileInfo.getId(),age, weight, height, imc, 'M');


                profileController.updateProfile(profile);
            }
        });


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
