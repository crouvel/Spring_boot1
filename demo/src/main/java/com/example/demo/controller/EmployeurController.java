package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employeur;
import com.example.demo.repository.EmployeurRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class EmployeurController {

    @Autowired
    private EmployeurRepository employeurRepository;

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("/employeurs/{userId}")
    public ResponseEntity<Employeur> createEmployeur(@PathVariable(value = "userId") Long userId,
                                                     @RequestBody Employeur employeurRequest) throws ResourceNotFoundException {
        Employeur employeur = userRepository.findById(userId).map(user -> {
            employeurRequest.setUser(user);
            //chercheurRequest.set
            return employeurRepository.save(employeurRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        return new ResponseEntity<>(employeur, HttpStatus.CREATED);
    }

    @PutMapping("/employeurs/{id}")
    public ResponseEntity<Employeur> updateEmployee(@PathVariable(value = "id") Long employeurId,
                                                   @Valid @RequestBody Employeur employeurDetails) throws ResourceNotFoundException {
        Employeur employeur = employeurRepository.findById(employeurId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeurId));
        employeur.setDescription(employeurDetails.getDescription());
        employeur.setNote(employeurDetails.getNote());
        employeur.setEntreprise(employeurDetails.getEntreprise());
        employeur.setAdresse(employeurDetails.getAdresse());
        final Employeur updatedEmployeur = employeurRepository.save(employeur);
        return ResponseEntity.ok(updatedEmployeur);
    }

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