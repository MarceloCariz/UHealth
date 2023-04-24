package main.java.com.uhealth.models;

public class Category {
    private int id;
    private String nombre;

    // get Categories - constructor
    public Category(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    // Post category - contructor
    public Category(String nombre){
        this.nombre = nombre;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}


