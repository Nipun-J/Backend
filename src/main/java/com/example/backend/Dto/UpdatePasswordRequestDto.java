package com.example.backend.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequestDto {

    private String email;
    private String newPassword;
}
