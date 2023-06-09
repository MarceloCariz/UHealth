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
    private JTable jTableFoods;
    private JPanel mainPanel;
    private JScrollPane scrollPanel;
    private JButton deleteFoodButton;
    private JButton updateFoodButton;
    private JPanel actionsPanel;

    DefaultTableModel model = new DefaultTableModel();
    ProductController productController = new ProductController();
    public FoodManager(){
        setContentPane(mainPanel);
        setSize(900, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Administrador-Administrador de comida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);
        this.userName.setText(Login.userName);
        this.userName.setText(Login.userName);
        jTableFoods = new JTable(model);
        scrollPanel.setViewportView(jTableFoods);
        //Definit columnas
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Calorias");
        model.addColumn("Carbohidratos");
        model.addColumn("Categoria");
        //LLenar tabla
        this.executeTableFoodManager();



        //Actualizar Comida
        this.updateFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFood();
            }
        });

        //Eliminar Comida
        this.deleteFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFood();
            }
        });



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

    private void updateFood(){
        int selectedFood = jTableFoods.getSelectedRow();

        if(selectedFood == -1) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
            return;
        };
        int id = (int) model.getValueAt(selectedFood, 0); // 0 = id
        String nameProduct = (String) model.getValueAt(selectedFood, 1); // 1 = nombre
        String caloriesString = (String) model.getValueAt(selectedFood, 2); //Calorias
        String carbsString = (String) model.getValueAt(selectedFood, 3); // carbs
        //Validacion si son float o numeros
        if(!caloriesString.matches("\\d+\\.?\\d*") || !carbsString.matches("\\d+\\.?\\d*")){
            JOptionPane.showMessageDialog(null, "Calorias y Carbohidratos deben ser numeros");
            return;
        }

        float calories = Float.parseFloat(caloriesString);
        float carbs = Float.parseFloat(carbsString);

        Product product = new Product(id, nameProduct, calories, carbs);

        productController.updateProduct(product);
        model.setRowCount(0);
        executeTableFoodManager();


    }
    private void deleteFood(){
        int selectedFood = jTableFoods.getSelectedRow();
        if(selectedFood == -1) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
            return;
        };

        int id = (int) model.getValueAt(selectedFood, 0); // 0 = id
        String nameProduct = (String) model.getValueAt(selectedFood, 1); // 1 = nombre

        int result = JOptionPane.showConfirmDialog(null, "Aceptar para eliminar la comida: "+ nameProduct , "Aceptar",JOptionPane.YES_NO_OPTION);

        if(result == JOptionPane.YES_OPTION){
            productController.deleteProduct(id);
            model.setRowCount(0);
            executeTableFoodManager();
        }

    }

    private void executeTableFoodManager(){


        List<Product> products = productController.getProducts();

        for (Product product : products){
            Object[] rowData = {product.getId(), product.getName(), Float.toString(product.getCalories()) ,Float.toString(product.getCarbs()) , product.getNombre()};
            model.addRow(rowData);
        }
        // todo: checkNombreFruta
    }


}
