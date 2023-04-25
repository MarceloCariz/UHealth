package main.java.com.uhealth.views.Admin;

import main.java.com.uhealth.controllers.ProductController;
import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.views.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FoodManager extends JFrame{
    private JPanel navBarPanel;
    private JLabel titleLabel;
    private JLabel userName;
    private JButton userManagerButton;
    private JButton addFoodButton;
    private JButton createUserButton;
    private JTable jTableUsers;
    private JPanel mainPanel;
    private JScrollPane scrollPanel;

    DefaultTableModel model = new DefaultTableModel();
    ProductController productController = new ProductController();
    public FoodManager(){
        setContentPane(mainPanel);
        setSize(900, 550);
        setResizable(false);
        setTitle("Administrador-Administrador de comida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);
        this.userName.setText(Login.userName);
        this.userName.setText(Login.userName);
        jTableUsers = new JTable(model);
        scrollPanel.setViewportView(jTableUsers);
        //LLenar tabla
        this.executeTableFoodManager();



        this.userManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UserManager().setVisible(true);
            }
        });

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
    }

    private void executeTableFoodManager(){
        //Definit columnas
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Calorias");
        model.addColumn("Carbohidratos");
        model.addColumn("Categoria");

        List<Product> products = productController.getProducts();

        for (Product product : products){
            Object[] rowData = {product.getId(), product.getName(), product.getCalories(), product.getCarbs(), product.getNombre()};
            model.addRow(rowData);
        }
        // todo: checkNombreFruta
    }
}
