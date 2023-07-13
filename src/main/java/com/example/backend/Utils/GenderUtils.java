package com.example.backend.Utils;

public class GenderUtils {

    public static String convertToShortForm(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            return "M";
        } else if (gender.equalsIgnoreCase("Female")) {
            return "F";
        } else {
            return null; // Return null or handle the default case as per your requirement
        }
    }
}
