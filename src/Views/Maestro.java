package Views;

import main.java.com.uhealth.views.Login;

import javax.swing.*;

public class Maestro extends JFrame{
    private JPanel panel1;
    private JPanel Navbar;
    private JLabel title;
    private JLabel masterName;
    private JButton profile;
    private JTable masterTable;
    private JButton solicitudesButton;

    public Maestro(){
        setContentPane(panel1);
        setSize(900, 550);
        setResizable(false);
        setTitle("Inicio de Sesion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.masterName.setText(Login.userName);

    }



}
