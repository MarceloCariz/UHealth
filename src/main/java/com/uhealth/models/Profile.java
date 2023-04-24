package main.java.com.uhealth.models;

public class Profile {
    private int id;

    private  int age;
    private float weight;

    private float height;

    private float imc;


    public Profile(int age, float weight, float height, float imc){
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.imc = imc;
    }


    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public float getImc() {
        return imc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }
}
