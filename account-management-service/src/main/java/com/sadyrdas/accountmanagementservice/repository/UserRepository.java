package com.sadyrdas.accountmanagementservice.repository;

import com.sadyrdas.accountmanagementservice.model.User;
import com.sadyrdas.accountmanagementservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(UserRole userRole);

    boolean existsByEmail(String email);

}
