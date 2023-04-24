package main.java.com.uhealth.service;

import main.java.com.uhealth.dao.UserDao;
import main.java.com.uhealth.models.User;

import javax.swing.*;
import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(){
        userDao = new UserDao();
    }
    public void createUser(User user) {
        if(userDao.checkEmail(user.getEmail())){
            JOptionPane.showMessageDialog(null, "El correo ya existe");
            return;
        }
        userDao.create(user);
        JOptionPane.showMessageDialog(null, "Usuario creado Correctamente");
    }

    public List<User> getUsers(){
        return  userDao.getUsers();
    }




}
