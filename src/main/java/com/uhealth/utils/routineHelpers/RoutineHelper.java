package main.java.com.uhealth.utils.routineHelpers;

import main.java.com.uhealth.models.Routine;

import java.util.List;
import java.util.stream.Collectors;

public class RoutineHelper {
    public List<Routine> getRoutinesByDate(List<Routine> routines,String date){

        List<Routine> routinesByDate = routines.stream().filter(routine -> routine.getDate().equals(date)).collect(Collectors.toList());

        return routinesByDate;

    }
}
