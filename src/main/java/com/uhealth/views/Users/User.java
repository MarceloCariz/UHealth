package main.java.com.uhealth.views.Users;

import Classes.Admin.*;
import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.views.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class User extends JFrame{
    private JPanel userPanel;
    private JLabel userName;
    private JLabel welcomeText;
    private JPanel navBar;
    private JButton routinesButton;
    private JButton submitButton;
    private JComboBox categoryBox;
    private JComboBox selectBox;
    private JLabel caloriesField;
    private JLabel caloriesText;
    private JLabel selectText;
    private JLabel categoryText;
    private JLabel scheduleField;
    private JLabel scheduleText;
    private JLabel dateField;
    private JLabel dateText;
    private JPanel foodContentMain;
    private JButton profileButton;

    public static String time;
    public static  String date;


    ProductsDAO productsDAO = new ProductsDAO();
    Product products = new Product();

    Routines routines = new Routines();

    RoutinesDAO routinesDAO = new RoutinesDAO();
    public User(){

        setContentPane(userPanel);
        setSize(600, 550);
        setResizable(false);
        setTitle("Cliente"+ Login.userName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName.setText(Login.userName);
        //Obtener horario y fecha
        this.Time();
        //LLenar comboBox con las categorias
        this.setCategories();
        //LLenar comBox con las verduras o frutas segun se seleccione la categoria
        this.categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameCategory = categoryBox.getSelectedItem().toString();
                int categoryId = Character.getNumericValue(nameCategory.charAt(0));
                // Limpiar resultados anteriores
                if(Product.products != null && !Product.products.isEmpty()){
                    selectBox.removeAllItems();
                }
//                products.setCategory(nameCategory);
                setProductsByCategory(categoryId);
                products.setCategoryId(categoryId);
            }
        });

        this.selectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectBox.getModel().getSize() == 0) return;
                String productName = selectBox.getSelectedItem().toString();
                products.setName(productName);
                float calories = productsDAO.getCaloriesByProduct(productName);
//                System.out.println(products.getCalories());
                products.setCalories(calories);
                String caloriesText = Float.toString(calories);
                caloriesField.setText(caloriesText);
            }
        });

        /// Enviar informacion  / crear rutina
        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String date = dateField.getText();
               String time = scheduleField.getText();
               int idProduct = Routines.idProduct;
               int idUser = Login.userId;
               Routines routines = new Routines(date, time, idProduct, idUser);
               routinesDAO.createRoutine(routines);
               JOptionPane.showMessageDialog(null, "Rutina creada Correctamente");
            }
        });

        this.routinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Food().setVisible(true);
            }
        });

        this.profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Profile().setVisible(true);
            }
        });

    }


    public void setCategories(){
        //Obtener categorias
        productsDAO.getCategories();
        //Agregar categorias al comboBox
        for(Object[] category : Product.categories){
            String nombre = category[0].toString() +"-"+category[1].toString();
            categoryBox.addItem(nombre); // nombre
        }
    }

    public void  setProductsByCategory(int categoryId){
        List<Product>  products =  productsDAO.getProductsByCategory(categoryId);
        for(Product product : products){
//            selectBox.setName(product.getId());// id
            selectBox.addItem(product.getName()); /// nombre
        }

    }
    public void Time(){
        // Hora
        LocalTime currentTime = LocalTime.now();
        // Fecha
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        this.dateField.setText(formattedDate);
        date = formattedDate;
        if(currentTime.isBefore(LocalTime.NOON)){
            this.scheduleField.setText("Mañana");
            time = "Mañana";
        }else if(currentTime.isBefore(LocalTime.of(18,0))){
            this.scheduleField.setText("Tarde");
            time = "Tarde";
        }else{
            this.scheduleField.setText("Noche");
            time = "Noche";
        }
    }

}
