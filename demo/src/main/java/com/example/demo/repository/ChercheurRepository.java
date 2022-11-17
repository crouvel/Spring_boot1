package com.example.demo.repository;

import com.example.demo.model.Chercheur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChercheurRepository extends JpaRepository<Chercheur, Long> {
    //Optional<User> findByUsername(String email);

    //Boolean existsByUsername(String username);

    //Boolean existsByEmail(String email);
}
