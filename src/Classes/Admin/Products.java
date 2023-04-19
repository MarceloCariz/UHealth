package Classes.Admin;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Products {

    private int id;
    private String category;
    private String name;
    private float calories;
    private float carbs; // carbohidratos

    public static List<Object[]> categories;

    public static List<Object[]> products;

    public Products(){};

    public Products(int id, String category, String name, float calories, float carbs){
        this.category = category;
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }


}
