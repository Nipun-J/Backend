package com.example.backend.Controller;

import com.example.backend.Dto.LoginRequestDto;
import com.example.backend.Dto.UpdatePasswordRequestDto;
import com.example.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequestDto) {
        System.out.println("Testing");
        System.out.println("Email: " + loginRequestDto.getEmail());
        //  login logic to the UserService
        return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @CrossOrigin
    @PostMapping(path= "/forgot-password")
    public ResponseEntity<Object> updatePassword(@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
        //  password update logic to the UserService
        System.out.println("Testing 2");
        return userService.updatePassword(updatePasswordRequestDto.getEmail(), updatePasswordRequestDto.getNewPassword());
    }
}

