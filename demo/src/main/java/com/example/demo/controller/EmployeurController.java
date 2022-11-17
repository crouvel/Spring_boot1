package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employeur;
import com.example.demo.repository.EmployeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeurController {

    @Autowired
    private EmployeurRepository employeurRepository;

    @GetMapping("/employeurs")
    public List<Employeur> getAllEmployeurs() {
        return employeurRepository.findAll();
    }

    @GetMapping("/employeurs/{id}")
    public ResponseEntity<Employeur> getEmployeurById(@PathVariable(value = "id") Long employeurId)
            throws ResourceNotFoundException {
        Employeur employeur = employeurRepository.findById(employeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeurId));
        return ResponseEntity.ok().body(employeur);
    }

    @PostMapping("/employeurs")
    public Employeur createEmployeur(@Valid @RequestBody Employeur employeur) {
        return employeurRepository.save(employeur);
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

    @DeleteMapping("/employeurs/{id}")
    public Map<String, Boolean> deleteEmployeur(@PathVariable(value = "id") Long employeurId)
            throws ResourceNotFoundException {
        Employeur employeur = employeurRepository.findById(employeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeurId));

        employeurRepository.delete(employeur);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}