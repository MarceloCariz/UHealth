package main.java.com.uhealth.views;
import javax.swing.*;
import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.views.Admin.Admin;
import main.java.com.uhealth.views.Users.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    private JPanel panel1;
    private JButton loginButton;
    private JLabel titleLogin;
    private JTextField userField;
    private JLabel userLabel;
    private JPasswordField passwordField;

    // Enviar datos entre interfaces / Variabled globales
    public static String userName = "";
    public static  int userId  = 0;
    String pass = "";

    public Login(){
        setContentPane(panel1);
        setSize(400, 550);
        setResizable(false);
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = userField.getText().trim();
                char[] getPassword = passwordField.getPassword();
                pass = new String(getPassword);
                if(userEmail.equals("") || pass.equals("")){
                    JOptionPane.showMessageDialog(null,"Debes llenar todos los campos");
                    return;
                }

                try {
                    // Conexion con la bd
                    Connection cn = Conexion.conectar();

                    // Preparar query
                    PreparedStatement pst  = cn.prepareStatement(
                            "select id_usuario, idRol, nombre from usuarios where email = '" + userEmail
                                + "' and password = '" + pass + "'"
                    );


                    ResultSet rs = pst.executeQuery();
//                    Conexion conexion = null;
//                    Conexion.closeAllConnections();

                    if(!rs.next()){
                        JOptionPane.showMessageDialog(null, "Datos de acceso incorrectos.");
                        return;
                    }

                    // Obtener id rol para despues evaluarlo
                    int idRol = rs.getInt("idRol");
                    userName = rs.getString("nombre");
                    userId = rs.getInt("id_usuario");


                    switch (idRol){
                        case 1:   /// 1  = admin
                            dispose(); //limpia el jframe
                            new Admin().setVisible(true);
                            break;
                        case 2: /// 2 = cliente
                            dispose();
                            new User().setVisible(true);
                            break;
                        default:
                            break;
                    }
                }catch (Exception err){
                    System.err.println("Error en el boton Acceder." + err);
                    JOptionPane.showMessageDialog(null, "Error al iniciar sesión.");
                }

            }
        });

    }


}
