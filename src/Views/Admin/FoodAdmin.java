package Views.Admin;

import Classes.Admin.Products;
import Classes.Admin.ProductsDAO;
import Views.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodAdmin extends JFrame{
    private JPanel panelFoodMain;
    private JPanel navBarPanel;
    private JButton addUsersButton;
    private JLabel titleLabel;
    private JLabel userName;
    private JLabel addTextLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel caloriesLabel;
    private JTextField caloriesField;
    private JTextField carbsField;
    private JLabel carbsLabel;
    private JLabel categoryLabel;
    private JComboBox categoryComboBox;
    private JButton submitButton;
    private JPanel formPanel;

    ProductsDAO productsDAO = new ProductsDAO();
    public FoodAdmin(){
        setContentPane(panelFoodMain);
        setSize(900, 550);
        setResizable(false);
        setTitle("Administrador-Agregar Comida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);
        // agregar categorias disponibles en el comboBox
        setCategories();


        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(nameField.getText().isEmpty() || carbsField.getText().isEmpty() || caloriesField.getText().isEmpty() ){
                    JOptionPane.showMessageDialog(null, "LLene todos los campos por favor");
                    return;
                }
                String name = nameField.getText();
                float carbs = Float.parseFloat(carbsField.getText());
                float calories  = Float.parseFloat(caloriesField.getText());
                String nameCategory = categoryComboBox.getSelectedItem().toString();
                int categoryId = Character.getNumericValue(nameCategory.charAt(0));
                System.out.println(name + calories + carbs + categoryId);


                Products product = new Products(name,  calories,carbs, categoryId);
                productsDAO.createProduct(product);
                JOptionPane.showMessageDialog(null, "Comida creada exitosamente");
                nameField.setText("");
                carbsField.setText("");
                caloriesField.setText("");
                categoryComboBox.setSelectedIndex(0);
            }
        });

        this.addUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Admin().setVisible(true);
            }
        });
    }


    public void setCategories(){
        //Obtener categorias
        productsDAO.getCategories();
        //Agregar categorias al comboBox
        for(Object[] category : Products.categories){
            String nombre = category[0].toString() +"-"+category[1].toString();
            categoryComboBox.addItem(nombre); // nombre
        }
    }
}
