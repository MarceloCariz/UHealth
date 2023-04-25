package main.java.com.uhealth.models;

import java.util.List;

public class Product extends Category{

    private int id;
    private String category;
    private int categoryId;
    protected String name;
    protected float calories;
    protected float carbs; // carbohidratos

    public static List<Object[]> categories;

    public static List<Product> products;

    public Product(){};

    //Actualizar producto - contructor
    public Product(int id ,String name,  float calories, float carbs){
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
    }

    //GetAll products
    public Product(int id ,String name,  float calories, float carbs, String category){
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
        this.nombre = category;
    }
    public Product(String name, float calories, float carbs, int categoryId){
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
        this.categoryId = categoryId;
    }

    //get Products user by category id
    public Product(int id, String name ,float calories){
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public float getCalories() {
        return calories;
    }

    public float getCarbs() {
        return carbs;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
