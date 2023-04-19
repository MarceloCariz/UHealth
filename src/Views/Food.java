package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Food extends JFrame {
    private JPanel foodPanel;
    private JPanel navBar;
    private JLabel welcomeText;
    private JLabel userName;
    private JLabel scheduleField;
    private JLabel scheduleText;
    private JLabel dataField;
    private JLabel dateText;
    private JButton foodButton;
    private JTable foodTable;

    public Food (){
        setContentPane(foodPanel);
        setSize(900, 550);
        setResizable(false);
        setTitle("Cliente"+Login.userName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);
        this.userName.setText(Login.userName);

        this.foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //limpia el jframe
                new User().setVisible(true);
            }
        });
    }
}
