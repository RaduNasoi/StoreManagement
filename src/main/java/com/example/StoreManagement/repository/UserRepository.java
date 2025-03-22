package com.example.StoreManagement.repository;

import com.example.StoreManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
