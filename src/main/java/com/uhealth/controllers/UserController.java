package main.java.com.uhealth.controllers;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.models.User;
import main.java.com.uhealth.service.UserService;

import java.sql.Connection;
import java.util.List;

public class UserController {

    private Connection conexion;
    private UserService userService;

    public UserController(){
        Connection cn = Conexion.conectar();
        conexion = cn;

        userService = new UserService();
    }
    public void addUser(User user){
        userService.createUser(user);
    }

    public void updateUser(User user) {userService.updateUser(user);}

    public void deleteUser(int userId) {userService.deleteUser(userId);}

    public List<User> getUsers(){
        return userService.getUsers();
    }


}
