package main.java.com.uhealth.utils.validations;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    public boolean validateEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            JOptionPane.showMessageDialog(null, "El email no es valido");
            return false;
        }
        return true;
    }

    public boolean isValidPhone(String phone){
        if(!phone.matches("\\d+") || (phone.length() == 9) == false){ //que significa "uno o más dígitos numéricos".
            JOptionPane.showMessageDialog(null, "Formato de numero incorrecto: Revise que sean numeros y no mayor o menor a 9 digitos");
            return false;
        }
        return true;
    }
}
