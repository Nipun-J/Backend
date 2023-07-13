package com.example.backend.Utils;



public class FindLoginId {
//    private static String adminId;
    public static String adminId;
    public static void setLoginId(String createdBy) {
        adminId = createdBy;
        System.out.println("set login value id = " + adminId);
    }

    public static String findLoginId() {
        System.out.println("util value id = " + adminId);
        return adminId;
    }
}