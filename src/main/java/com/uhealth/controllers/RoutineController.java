package main.java.com.uhealth.controllers;

import main.java.com.uhealth.models.Routine;
import main.java.com.uhealth.service.RoutineService;

public class RoutineController {
    private RoutineService routineService;


    public RoutineController(){
        routineService = new RoutineService();
    }

    public void createRoutine(Routine routine){
        routineService.createRoutine(routine);
    }
}
