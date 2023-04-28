package main.java.com.uhealth.models;

public class Routine extends Product{
    private int id;
    
    private String date;

    private String schedule;

    private int idUser;

    private  int idProduct;


    public Routine(){}

    // Crear rutina  - contructor
    public Routine(String date, String schedule, int idProduct, int idUser){
        this.date = date;
        this.schedule = schedule;
        this.idProduct = idProduct;
        this.idUser = idUser;
    }

    // Obtener rutina  - contructor
    public Routine(int id , String date, String schedule, String name , float calories, float carbs){
        this.id = id;
        this.date = date;
        this.schedule = schedule;
        this.name = name;
        this.calories = calories;
        this.carbs =carbs;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getIdUser() {
        return idUser;
    }
}
