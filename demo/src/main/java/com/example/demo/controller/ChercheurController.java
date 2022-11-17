package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Chercheur;
import com.example.demo.repository.ChercheurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ChercheurController {
    @Autowired
    private ChercheurRepository chercheurRepository;

    @GetMapping("/chercheurs")
    public List<Chercheur> getAllChercheurs() {
        return chercheurRepository.findAll();
    }

    @GetMapping("/chercheurs/{id}")
    public ResponseEntity<Chercheur> getChercheurById(@PathVariable(value = "id") Long chercheurId)
            throws ResourceNotFoundException {
        Chercheur chercheur = chercheurRepository.findById(chercheurId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + chercheurId));
        return ResponseEntity.ok().body(chercheur);
    }

    @PostMapping("/chercheurs")
    public Chercheur createChercheur(@Valid @RequestBody Chercheur chercheur) {
        return chercheurRepository.save(chercheur);
    }

    /*@PutMapping("/users/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long userId,
                                                   @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        user.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }*/

    @DeleteMapping("/chercheurs/{id}")
    public Map<String, Boolean> deleteEmployeur(@PathVariable(value = "id") Long chercheurId)
            throws ResourceNotFoundException {
        Chercheur chercheur = chercheurRepository.findById(chercheurId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + chercheurId));

        chercheurRepository.delete(chercheur);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}