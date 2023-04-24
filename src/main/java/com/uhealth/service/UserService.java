package main.java.com.uhealth.service;
import main.java.com.uhealth.dao.UserDao;
import main.java.com.uhealth.models.User;

import javax.swing.*;
import java.util.List;

public class UserService extends ProfileService{

    private UserDao userDao;
    private ProfileService profileService;

    public UserService(){

        userDao = new UserDao();
        profileService = new ProfileService();
    }
    public void createUser(User user) {
        //Validar correo
        if(userDao.checkEmail(user.getEmail())){
            JOptionPane.showMessageDialog(null, "El correo ya existe");
            return;
        }
        //Obtener id perfil
        int idProfile = profileService.createProfile();
        //Correcto
        if(userDao.create(user, idProfile)){
            JOptionPane.showMessageDialog(null, "Usuario creado Correctamente");
            return;
        }
        // Error
        JOptionPane.showMessageDialog(null, "Hubo un error en la creacion del usuario");
    }


    public List<User> getUsers(){
        return  userDao.getUsers();
    }


    public User getUserById(int userId){
        return userDao.getUserById(userId);
    }




}
