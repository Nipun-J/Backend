package com.example.backend.Service;

import com.example.backend.Dto.LoginRequestDto;
import com.example.backend.Dto.UserResponseDto;
import com.example.backend.Dto.UserSaveRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<Object> UserSave(UserSaveRequestDto userSaveRequestDto);

    ResponseEntity<Object> login(String email, String password);

    ResponseEntity<Object> updatePassword(String email, String newPassword);

    List<UserResponseDto> getAllUsers();

    ResponseEntity<Object> deleteUser(Integer uid);

    ResponseEntity<Object> updateUser(Integer uid, UserSaveRequestDto userSaveRequestDto);

    Map<String, Long> getGenderCounts();

    Map<String, Long> getServiceProvidersCount();

    Long countActiveRegistrations();

    Long countInactiveRegistrations();

}
