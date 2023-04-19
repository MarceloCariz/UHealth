package Views;
import javax.swing.*;
import Classes.Database.Conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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

    // Enviar datos entre interfaces
    public static String userName = "";
    String pass = "";

    public Login(){
        setContentPane(panel1);
        setSize(400, 550);
        setResizable(false);
        setTitle("Inicio de Sesion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = userField.getText().trim();
                char[] getPassword = passwordField.getPassword();
                pass = new String(getPassword);
                if(userEmail.equals("") || pass.equals("")){
                    // TODO: mostrar algo
                    JOptionPane.showMessageDialog(null,"Debes llenar todos los campos");
                    return;
                }

                try {
                    // Conexion con la bd
                    Connection cn = Conexion.conectar();
                    //
                    PreparedStatement pst  = cn.prepareStatement(
                            "select idRol, nombre from usuarios where email = '" + userEmail
                                + "' and password = '" + pass + "'"
                    );


                    ResultSet rs = pst.executeQuery();

                    if(!rs.next()){
                        JOptionPane.showMessageDialog(null, "Datos de acceso incorrectos.");
                        userField.setText("");
                        passwordField.setText("");
                    }

                    // Obtener id rol para despues evaluarlo
                    int idRol = rs.getInt("idRol");
                    userName = rs.getString("nombre");
                    switch (idRol){
                        case 1:   /// 1  = admin
                            dispose(); //limpia el jframe
                            new Admin().setVisible(true);
                            break;
                        case 2: /// 2 = cliente
                            dispose();
                            break;
                        case 3: /// 3 = maestro
                            dispose();
                            new Maestro().setVisible(true);
//                            new Maestro().setMasterName(masterName);
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
