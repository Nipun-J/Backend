package com.example.backend.Utils;

public class PhoneNumberUtils {
//    private static final String CODE = "94";
//
//    public static String formatPhoneNumber(String phoneNumber) {
//        // Remove any non-digit characters from the phone number
//        String digitsOnly = phoneNumber.replaceAll("[^0-9]", "");
//
//        // Check if the phone number starts with 0 or 7
//        if (digitsOnly.startsWith("0") || digitsOnly.startsWith("7")) {
//            // Remove the leading 0 or 7 and prepend the country code
//            return CODE + digitsOnly.substring(digitsOnly.startsWith("0") ? 1 : 0);
//        }
//
//        // code
//        return digitsOnly;
//    }

    public static String formatPhoneNumber(String phoneNumber) {

        if (phoneNumber == null) {
            // Handle the null case
            return null;
        }

        String cleanedPhoneNumber = phoneNumber.replaceAll("\\D", "");

        if (cleanedPhoneNumber.startsWith("947")) {
            // Phone number is already in the format (947********)
            if (cleanedPhoneNumber.length() >= 12) {
                cleanedPhoneNumber = cleanedPhoneNumber.substring(0, 12);
            }
        } else if (cleanedPhoneNumber.startsWith("+947")) {
            // Remove the leading "+"
            if (cleanedPhoneNumber.length() >= 15) {
                cleanedPhoneNumber = "947" + cleanedPhoneNumber.substring(4, 15);
            }
        } else if (cleanedPhoneNumber.startsWith("07")) {
            // Convert the format 07******** to 947********
            if (cleanedPhoneNumber.length() >= 10) {
                cleanedPhoneNumber = "947" + cleanedPhoneNumber.substring(2);
            }
        } else if (cleanedPhoneNumber.startsWith("7")) {
            // Convert the format 7******** to 947********
            if (cleanedPhoneNumber.length() >= 9) {
                cleanedPhoneNumber = "947" + cleanedPhoneNumber.substring(1);
            }
        }

        return cleanedPhoneNumber;
    }



}
