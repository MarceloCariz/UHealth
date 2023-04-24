package main.java.com.uhealth.controllers;

import main.java.com.uhealth.Database.Conexion;
import main.java.com.uhealth.models.User;
import main.java.com.uhealth.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public List<User> getUsers(){
        return userService.getUsers();
    }


}
