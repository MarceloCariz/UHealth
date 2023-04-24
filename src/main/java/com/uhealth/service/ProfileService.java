package main.java.com.uhealth.service;

import main.java.com.uhealth.dao.ProfileDao;
import main.java.com.uhealth.models.Profile;
import main.java.com.uhealth.models.User;

import javax.swing.*;

public class ProfileService {
    private ProfileDao profileDao;
    public ProfileService(){

        profileDao = new ProfileDao();
    }

    public void updateProfile(Profile profile) {


        if(profileDao.updateProfile(profile)){
            JOptionPane.showMessageDialog(null, "Perfil actualizado correctamente");
            return;
        }
        JOptionPane.showMessageDialog(null, "Hubo un error al actualizar el perfil");
    }


    public int createProfile(){
        return profileDao.createProfile();
    }


    public Profile getProfile(int userId){
        return  profileDao.getProfile(userId);
    }
}
