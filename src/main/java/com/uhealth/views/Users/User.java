package main.java.com.uhealth.views.Users;

import Classes.Admin.*;
import main.java.com.uhealth.controllers.CategoryController;
import main.java.com.uhealth.controllers.ProductController;
import main.java.com.uhealth.controllers.RoutineController;
import main.java.com.uhealth.models.Category;
import main.java.com.uhealth.models.Product;
import main.java.com.uhealth.models.Routine;
import main.java.com.uhealth.views.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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


    CategoryController categoryController = new CategoryController();
    RoutineController routineController = new RoutineController();

    ProductController productController = new ProductController();
    private  List<Product>  products;

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
                selectBox.removeAllItems();
                setProductsByCategory(categoryId);
            }
        });

        this.selectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectBox.getModel().getSize() == 0) return;
                String productName = selectBox.getSelectedItem().toString();
                Product product = getInfoByProductName(productName);
                String calories = Float.toString(product.getCalories());
                caloriesField.setText(calories);
            }
        });

        /// Enviar informacion  / crear rutina
        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = selectBox.getSelectedItem().toString();
                Product productInfo = getInfoByProductName(productName);
                String date = dateField.getText();
                String time = scheduleField.getText();
                int idProduct = productInfo.getId();
                int idUser = Login.userId;
                Routine routine = new Routine(date, time, idProduct, idUser);
                routineController.createRoutine(routine);
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
        List<Category> categories = categoryController.getCategories();
        //Agregar categorias al comboBox
        for(Category category : categories){
            String name = category.getId() +"-"+category.getNombre().toString();
            categoryBox.addItem(name); // nombre
        }
    }



    public void  setProductsByCategory(int categoryId){
        products = productController.getProductsByCategoryId(categoryId);
        for(Product product : products){
            selectBox.addItem(product.getName()); /// nombre
        }
    }

    private Product getInfoByProductName(String productName){
        Optional<Product>  productsFilter = products.stream()
                .filter(product -> product.getName().equals(productName)).findFirst();
        if(productsFilter.isPresent()){
            Product productInfo = productsFilter.get();
            return  productInfo;
        }

        return  productsFilter.get();
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
