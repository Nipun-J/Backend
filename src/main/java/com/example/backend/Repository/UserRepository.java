package com.example.backend.Repository;

import com.example.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    List<User> findByIsDeletedFalse();

//    Long countByGender(String gender);
//
//    Long countByServiceProvider(String serviceProvider);

    Long countByIsDeletedFalse();

    Long countByIsDeletedTrue();

    Long countByGenderAndIsDeleted(String gender, Boolean isDeleted);

    Long countByServiceProviderAndIsDeleted(String serviceProvider , Boolean isDeleted);

}
