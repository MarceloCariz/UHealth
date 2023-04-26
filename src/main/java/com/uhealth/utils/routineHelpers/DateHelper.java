package main.java.com.uhealth.utils.routineHelpers;

import main.java.com.uhealth.models.Routine;

import java.util.List;
import java.util.stream.Collectors;

public class DateHelper {

    public List<String> getDates(List<Routine> routines){
        List<String> datesRoutine = routines.stream()
                .map(routine -> routine.getDate().toString())
                .distinct()
                .collect(Collectors.toList());
        return datesRoutine;
    }
}
