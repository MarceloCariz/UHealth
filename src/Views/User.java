package Views;

import Classes.Admin.Products;
import Classes.Admin.ProductsDAO;

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
    private JButton accionButton;
    private JButton agregarButton;
    private JComboBox categoryBox;
    private JComboBox selectBox;
    private JLabel caloriesField;
    private JLabel caloriesText;
    private JLabel selectText;
    private JLabel categoryText;
    private JLabel scheduleField;
    private JLabel scheduleText;
    private JLabel dataField;
    private JLabel dateText;


    ProductsDAO productsDAO = new ProductsDAO();
    public User(){

        setContentPane(userPanel);
        setSize(600, 550);
        setResizable(false);
        setTitle("Cliente"+Login.userName);
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
                // Limpiar resultados anteriores
                selectBox.removeAllItems();
                String nameCategory = categoryBox.getSelectedItem().toString();
                int categoryId = Character.getNumericValue(nameCategory.charAt(0));
                setProductsByCategory(categoryId);

            }
        });

    }

    public String getNameCategory(){
        return categoryBox.getSelectedItem().toString();
    }
    public void setCategories(){
        //Obtener categorias
        productsDAO.getCategories();
        //Agregar categorias al comboBox
        for(Object[] category : Products.categories){
            String nombre = category[0].toString() + category[1].toString();
            categoryBox.addItem(nombre); // nombre
        }
    }

    public void  setProductsByCategory(int categoryId){
        productsDAO.getProductsByCategory(categoryId);
        for(Object[] product : Products.products){
            selectBox.setName(product[0].toString());// id
            selectBox.addItem(product[1]); /// nombre
        }

    }
    public void Time(){
        // Hora
        LocalTime currentTime = LocalTime.now();
        // Fecha
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        this.dataField.setText(formattedDate);
        if(currentTime.isBefore(LocalTime.NOON)){
            this.scheduleField.setText("Mañana");
        }else if(currentTime.isBefore(LocalTime.of(18,0))){
            this.scheduleField.setText("Tarde");
        }else{
            this.scheduleField.setText("Noche");
        }
    }

}
