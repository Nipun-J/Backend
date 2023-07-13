package com.example.backend.Utils;


import java.sql.Timestamp;
import java.util.Date;

public class DateTime {

    public static Timestamp CurrentTimestampGet() {
        return new Timestamp(new Date().getTime());
    }
}
