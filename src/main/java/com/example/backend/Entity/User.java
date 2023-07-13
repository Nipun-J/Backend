package com.example.backend.Entity;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "register")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "serviceProvider")
    private String serviceProvider;

    @Column(name = "nic")
    private String nic;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdTime")
    private Timestamp createdTime;

    @Column(name = "updatedTime")
    private Timestamp updatedTime;

    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "isDeleted")
    private boolean isDeleted;

//    @Column(name = "createdTime", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime createdTime;

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }


    // Constructor, getters, and setters
}
