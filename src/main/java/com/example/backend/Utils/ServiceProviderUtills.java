package com.example.backend.Utils;

public class ServiceProviderUtills {

    public static String checkServiceProvider(String formattedPhoneNumber) {

        if (formattedPhoneNumber == null) {
            // Handle the null case
            return null;
        }
        if (formattedPhoneNumber.startsWith("9477") || formattedPhoneNumber.startsWith("9474") || formattedPhoneNumber.startsWith("9476")) {
            return "Dialog";
        } else if (formattedPhoneNumber.startsWith("9470") || formattedPhoneNumber.startsWith("9471")) {
            return "Mobitel";
        } else if (formattedPhoneNumber.startsWith("9478") || formattedPhoneNumber.startsWith("9472")) {
            return "Hutch";
        } else if (formattedPhoneNumber.startsWith("9475")) {
            return "Airtel";
        } else {
            return "Unknown";
        }
    }
}
