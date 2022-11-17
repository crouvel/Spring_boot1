package com.example.demo.repository;


import com.example.demo.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    //Optional<User> findByUsername(String email);

    //Boolean existsByUsername(String username);

    //Boolean existsByEmail(String email);
}