package main.java.com.uhealth.controllers;
import main.java.com.uhealth.models.Routine;
import main.java.com.uhealth.service.RoutineService;
import java.util.List;


public class RoutineController {
    private RoutineService routineService;


    public RoutineController(){
        routineService = new RoutineService();
    }

    public void createRoutine(Routine routine){
        routineService.createRoutine(routine);
    }

    public List<Routine> getRoutinesByUser(int userId){
        return  routineService.getRoutinesByUser(userId);
    }
}
