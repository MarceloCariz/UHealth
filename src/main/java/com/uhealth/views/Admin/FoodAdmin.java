package main.java.com.uhealth.views.Admin;

import main.java.com.uhealth.controllers.CategoryController;
import main.java.com.uhealth.controllers.ProductController;
import main.java.com.uhealth.models.Category;
import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.views.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private JButton usersMangerButton;
    private JButton foodManagerButton;


    public FoodAdmin(){
        setContentPane(panelFoodMain);
        setSize(900, 550);
        setResizable(false);
        setTitle("Administrador-Agregar Comida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);
        // agregar categorias disponibles en el comboBox
        setCategories();

        ProductController productController = new ProductController();


        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Validacion campos vacios
                boolean isEmpty = validateForm();
                if(!isEmpty) return;

                //Validacion numero calories y carbs
                boolean isValidNumber = validateCarbsAndCalories();
                if(!isValidNumber) return;

                String name = nameField.getText();
                float carbs = Float.parseFloat(carbsField.getText());
                float calories  = Float.parseFloat(caloriesField.getText());
                String nameCategory = categoryComboBox.getSelectedItem().toString();
                int categoryId = Character.getNumericValue(nameCategory.charAt(0));
                System.out.println(name + calories + carbs + categoryId);


                Product product = new Product(name,  calories,carbs, categoryId);
                productController.addProduct(product);
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

        this.foodManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FoodManager().setVisible(true);
            }
        });

        this.usersMangerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UserManager().setVisible(true);
            }
        });
    }


    private void setCategories(){
        CategoryController categoryController =  new CategoryController();
        //Obtener categorias
        List<Category>  categories = categoryController.getCategories();
        //Agregar categorias al comboBox
        for(Category category : categories){
            String name = category.getId() +"-"+category.getNombre().toString();
            categoryComboBox.addItem(name); // nombre
        }
    }

    private boolean validateForm(){
        if(nameField.getText().isEmpty() || carbsField.getText().isEmpty() || caloriesField.getText().isEmpty()){
//            if(caloriesField.getText().)
            JOptionPane.showMessageDialog(null, "LLene todos los campos por favor");
            return false;
        }
        return true;
    }

    private  boolean validateCarbsAndCalories(){
        String carbs = carbsField.getText().toString();
        String calories = caloriesField.getText().toString();
        if(!carbs.matches("\\d+") || !calories.matches("\\d+")){ //que significa "uno o más dígitos numéricos".
            JOptionPane.showMessageDialog(null, "Calorias o Carbohidratos deben ser numeros");
            return false;
        }
        return true;
    }

}
