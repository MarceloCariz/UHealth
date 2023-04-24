package main.java.com.uhealth.models;

public class User {

    private int id;
    private String name;
    private String email;
    private  String password;
    private String phone;
    private int idRol;

    public User(){};


    // Constructor general
    public User(int id, String name, String email, String password, String phone, int idRol){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.idRol = idRol;
    }

    // Constructor obtener
    public User(int id, String name, String email, String phone, int idRol){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.idRol = idRol;
    }

    // Constructor crear
    public User( String name, String email ,String password , String phone, int idRol){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.idRol = idRol;
    }


    //getters y setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getIdRol() {
        return idRol;
    }
}
