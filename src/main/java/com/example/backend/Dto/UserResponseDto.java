package com.example.backend.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Integer uid;
    private String name;
    private String address;
    private String phoneNumber;
    private String nic;
//new
    private String birthday;
    private Integer age;
    private String gender;
}

