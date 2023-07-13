package com.example.backend.ServiceImpl;

import com.example.backend.Dto.UserResponseDto;
import com.example.backend.Dto.UserSaveRequestDto;
import com.example.backend.Entity.Admin;
import com.example.backend.Entity.User;
import com.example.backend.Repository.AdminRepository;
import com.example.backend.Repository.UserRepository;
import com.example.backend.Service.UserService;
import com.example.backend.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

import static com.example.backend.Utils.FindLoginId.adminId;
import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;



    @Override
    public ResponseEntity<Object> UserSave(UserSaveRequestDto userSaveRequestDto) {
        // Format the phone number
        String formattedPhoneNumber = PhoneNumberUtils.formatPhoneNumber(userSaveRequestDto.getPhoneNumber());

        // check the service provider
        String serviceProvider = ServiceProviderUtills.checkServiceProvider(formattedPhoneNumber);

        User user = new User();
        user.setName(userSaveRequestDto.getName());
        user.setAddress(userSaveRequestDto.getAddress());
        user.setPhoneNumber(formattedPhoneNumber);
        user.setServiceProvider(serviceProvider);
        user.setNic(userSaveRequestDto.getNic());
        user.setBirthday(userSaveRequestDto.getBirthday());
        user.setAge(userSaveRequestDto.getAge());

        // Convert gender to short form using GenderUtils
        String genderShortForm = GenderUtils.convertToShortForm(userSaveRequestDto.getGender());
        user.setGender(genderShortForm);

        user.setCreatedTime(com.example.backend.Utils.DateTime.CurrentTimestampGet());


        user.setCreatedBy(adminId);
        userRepository.save(user);

        return ResponseHandler.generateResponse(HttpStatus.OK, Constants.SUCCESS);
    }



    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findByIsDeletedFalse();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setUid(user.getUid());
            userResponseDto.setName(user.getName());
            userResponseDto.setAddress(user.getAddress());
            userResponseDto.setPhoneNumber(user.getPhoneNumber());
            userResponseDto.setNic(user.getNic());

            userResponseDto.setBirthday(user.getBirthday());
            userResponseDto.setAge(user.getAge());
            userResponseDto.setGender(user.getGender());

            userResponseDtos.add(userResponseDto);
        }

        return userResponseDtos;
    }

    @Override
    public ResponseEntity<Object> deleteUser(Integer uid) {
        User user = userRepository.findById(uid).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        user.setIsDeleted(true);
        userRepository.save(user);

        return ResponseEntity.ok("User deleted successfully");
    }

    @Override
    public ResponseEntity<Object> updateUser(Integer uid, UserSaveRequestDto userSaveRequestDto) {
        User user = userRepository.findById(uid).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Format the phone number
        String formattedPhoneNumber = PhoneNumberUtils.formatPhoneNumber(userSaveRequestDto.getPhoneNumber());

        // check the service provider
        String serviceProvider = ServiceProviderUtills.checkServiceProvider(formattedPhoneNumber);

        // Update the user data
        user.setName(userSaveRequestDto.getName());
        user.setAddress(userSaveRequestDto.getAddress());
        user.setPhoneNumber(formattedPhoneNumber);
        user.setServiceProvider(serviceProvider);
        user.setNic(userSaveRequestDto.getNic());
        user.setBirthday(userSaveRequestDto.getBirthday());
        user.setAge(userSaveRequestDto.getAge());

        // Convert gender to short form using GenderUtils
        String genderShortForm = GenderUtils.convertToShortForm(userSaveRequestDto.getGender());
        user.setGender(genderShortForm);

        user.setUpdatedTime(com.example.backend.Utils.DateTime.CurrentTimestampGet());

        user.setUpdatedBy(adminId);

        userRepository.save(user);

        return ResponseHandler.generateResponse(HttpStatus.OK, "User updated successfully");
    }

//    @Override
//    public Map<String, Long> getGenderCounts() {
//        Map<String, Long> genderCounts = new HashMap<>();
//
//        Long femaleCount = userRepository.countByGender("F");
//        Long maleCount = userRepository.countByGender("M");
//
//        genderCounts.put("Female", femaleCount);
//        genderCounts.put("Male", maleCount);
//
//        return genderCounts;
//    }

    @Override
    public Map<String, Long> getGenderCounts() {
        Map<String, Long> genderCounts = new HashMap<>();

        Long femaleCount = userRepository.countByGenderAndIsDeleted("F", false);
        Long maleCount = userRepository.countByGenderAndIsDeleted("M", false);

        genderCounts.put("Female", femaleCount);
        genderCounts.put("Male", maleCount);

        return genderCounts;
    }


//    @Override
//    public Map<String, Long> getServiceProvidersCount() {
//        Map<String, Long> serviceProvidersCounts = new HashMap<>();
//
//        List<String> serviceProviders = Arrays.asList("Hutch", "Dialog", "Airtel", "Mobitel");
//        for (String serviceProvider : serviceProviders) {
//            Long count = userRepository.countByServiceProvider(serviceProvider);
//            serviceProvidersCounts.put(serviceProvider, count);
//        }
//
//        return serviceProvidersCounts;
//    }

    @Override
    public Map<String, Long> getServiceProvidersCount() {
        Map<String, Long> serviceProvidersCounts = new HashMap<>();

        List<String> serviceProviders = Arrays.asList("Hutch", "Dialog", "Airtel", "Mobitel");
        for (String serviceProvider : serviceProviders) {
            Long count = userRepository.countByServiceProviderAndIsDeleted(serviceProvider, false);
            serviceProvidersCounts.put(serviceProvider, count);
        }

        return serviceProvidersCounts;
    }



    @Override
    public Long countActiveRegistrations() {
        return userRepository.countByIsDeletedFalse();
    }

    @Override
    public Long countInactiveRegistrations() {
        return userRepository.countByIsDeletedTrue();
    }


//    @Override
//    public ResponseEntity<Object> login(String email, String password) {
//        // Retrieve the user based on the provided email
//        Admin user = adminRepository.findByEmail(email);
//
//        // Check if user exists and password matches
//        if (user != null && user.getPassword().equals(password)) {
//
//
//
//            return ResponseHandler.generateResponse(HttpStatus.OK, "Login successful");
//        } else {
//            return ResponseHandler.generateResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials");
//        }
//    }



    @Override
    public ResponseEntity<Object> login(String email, String password) {
        // Retrieve the user based on the provided email
        Admin user = adminRepository.findByEmail(email);

        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            FindLoginId.adminId = String.valueOf(user.getAdminId());

            return ResponseHandler.generateResponse(HttpStatus.OK, "Login successful");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }



    @Override
    public ResponseEntity<Object> updatePassword(String email, String newPassword) {
        // Retrieve the admin user based on the provided email
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin user not found");
        }

        // Update  password
        admin.setPassword(newPassword);

        admin.setUpdatedTime(com.example.backend.Utils.DateTime.CurrentTimestampGet());

        adminRepository.save(admin);

        return ResponseEntity.ok("Password updated successfully");
    }


}
