package main.java.com.uhealth.service;

import main.java.com.uhealth.dao.RoutineDao;
import main.java.com.uhealth.models.Routine;

import javax.swing.*;

public class RoutineService {
    private RoutineDao routineDao;

    public RoutineService(){
        routineDao = new RoutineDao();
    }

    public void createRoutine(Routine routine){
        if(routineDao.create(routine)){
            JOptionPane.showMessageDialog(null, "Rutina creada correctamente");
            return;
        }
        JOptionPane.showMessageDialog(null, "Hubo un error en la creacion de la rutina");
    }
}
