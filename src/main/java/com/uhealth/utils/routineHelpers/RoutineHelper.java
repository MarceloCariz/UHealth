package main.java.com.uhealth.utils.routineHelpers;

import main.java.com.uhealth.models.Routine;

import java.util.List;
import java.util.stream.Collectors;

public class RoutineHelper {
    public List<Routine> getRoutinesByDate(List<Routine> routines,String date){
        List<Routine> routinesByDate = routines.stream().filter(routine -> routine.getDate().equals(date)).collect(Collectors.toList());
        return routinesByDate;
    }
    public String getTotalCalories(List<Routine> routines){
        float caloriesFloat = (float) routines.stream().mapToDouble(routine -> routine.getCalories()).sum(); // El (float) lo covierte , es un casting , el map devuelve double y lo convierte en floar
        String totalCalories = Float.toString(caloriesFloat);
        return totalCalories;
    }

    public String getTotalCarbs(List<Routine> routines){
        float carbsFloat = (float) routines.stream().mapToDouble(routine -> routine.getCarbs()).sum();
        String totalCarbs = Float.toString(carbsFloat);
        return totalCarbs;
    }
}
