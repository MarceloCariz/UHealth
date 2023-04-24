package main.java.com.uhealth.models;

public class Routine {
    private int id;
    private String date;
    private String schedule;

//    private int idProduct;

    private int idUser;

    public static int idProduct; // Routine routein = new Routines(); Routine.idProduct

    public Routine(){}

    // Crear rutina  - contructor
    public Routine(String date, String schedule, int idProduct, int idUser){
        this.date = date;
        this.schedule = schedule;
        this.idProduct = idProduct;
        this.idUser = idUser;
    }

    // Obtener rutina  - contructor
    public Routine(int id , String date, String schedule, int idProduct, int idUser){
        this.id = id;
        this.date = date;
        this.schedule = schedule;
        this.idProduct = idProduct;
        this.idUser = idUser;
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
