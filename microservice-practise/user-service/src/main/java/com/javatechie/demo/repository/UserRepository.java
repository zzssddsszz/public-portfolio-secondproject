package com.javatechie.demo.repository;

import com.javatechie.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User")
    List<User> searchAll();
}
