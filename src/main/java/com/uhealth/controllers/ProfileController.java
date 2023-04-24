package main.java.com.uhealth.controllers;

import main.java.com.uhealth.models.Profile;
import main.java.com.uhealth.service.ProfileService;

public class ProfileController {

    private ProfileService profileService;

    public ProfileController(){
        profileService = new ProfileService();
    }


    public void updateProfile(Profile profile){
        profileService.updateProfile(profile);
    }

    public Profile getProfile(int userId){
        return profileService.getProfile(userId);
    }



}
