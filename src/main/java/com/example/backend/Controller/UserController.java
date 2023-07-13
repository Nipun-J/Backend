package com.example.backend.Controller;

import com.example.backend.Dto.UserResponseDto;
import com.example.backend.Dto.UserSaveRequestDto;
import com.example.backend.Service.UserService;
import com.example.backend.Utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("v1/user")
public class UserController
{
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(path ="/Save")
    public ResponseEntity<Object> UserSave(@RequestBody UserSaveRequestDto userSaveRequestDto)
    {
        System.out.println("Testing");
        System.out.println("Name: " + userSaveRequestDto.getName());
        System.out.println("Address: " + userSaveRequestDto.getAddress());
        return userService.UserSave(userSaveRequestDto);

    }


    @GetMapping(path = "/view")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping(path = "/delete/{uid}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer uid) {
        ResponseEntity<Object> response = userService.deleteUser(uid);
        return response;
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Object> updateUser(@PathVariable("uid") Integer uid, @RequestBody UserSaveRequestDto userSaveRequestDto) {
        ResponseEntity<Object> response = userService.updateUser(uid, userSaveRequestDto);
        System.out.println("Testing");
        return response;
    }

    @GetMapping("/gender-counts")
    public ResponseEntity<Map<String, Long>> getGenderCounts() {
        Map<String, Long> genderCounts = userService.getGenderCounts();
        System.out.println("Testing");
        return ResponseEntity.ok(genderCounts);
    }


    @GetMapping("/service-provider-counts")
    public ResponseEntity<Map<String, Long>> getServiceProviderCounts() {
        Map<String, Long> serviceProviderCounts = userService.getServiceProvidersCount();
        System.out.println("Testing");
        return ResponseEntity.ok(serviceProviderCounts);
    }

    @GetMapping("/active-registration-count")
    public ResponseEntity<Long> getActiveRegistrationCount() {
        Long count = userService.countActiveRegistrations();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/inactive-registration-count")
    public ResponseEntity<Long> getInactiveRegistrationCount() {
        Long count = userService.countInactiveRegistrations();
        return ResponseEntity.ok(count);
    }


}

