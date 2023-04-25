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
    private JComboBox genderComboBox;
    private JLabel genderLabel;

    public ProfileV(){
        setContentPane(profileMainPanel);
        setSize(600, 550);
        setResizable(false);
        setTitle("Perfil - "+ Login.userName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);
        ProfileController profileController =  new ProfileController();
        Profile profileInfo = profileController.getProfile(Login.userId);

        //Llenar form
        fillFormProfile(profileInfo);


        this.saveProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int age = Integer.parseInt(ageField.getText());
                float weight = Float.parseFloat(weightField.getText());
                float height = Float.parseFloat(heightField.getText());

                float imc  = weight / (height * height);// peso / altura * altura
                imcField.setText(Float.toString(imc));

                char gender = getGenderComboBox();


                Profile profile = new Profile(profileInfo.getId(),age, weight, height, imc, gender);


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

        private char getGenderComboBox(){
        String selectComboBox = genderComboBox.getSelectedItem().toString();
        if(selectComboBox == "Hombre"){
            return 'H';
        }else {
            return 'M';
        }
    }
    private void fillFormProfile(Profile profile){
        String age = Integer.toString(profile.getAge());
        String weight = Float.toString(profile.getWeight());
        String height = Float.toString(profile.getHeight());
        String imc = Float.toString(profile.getImc());
        char gender = profile.getGender();
        if(gender == 'H'){
            genderComboBox.setSelectedIndex(0);
        }
        if(gender == 'M'){
            genderComboBox.setSelectedIndex(1);
        }


        ageField.setText(age);
        weightField.setText(weight);
        heightField.setText(height);
        imcField.setText(imc);
    }
}
