package acme.oddo.utils;

import org.springframework.stereotype.Controller;

public class CheckValidInput {
    

    public static boolean isStringNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
