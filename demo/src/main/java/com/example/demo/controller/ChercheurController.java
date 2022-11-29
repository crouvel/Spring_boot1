package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Chercheur;
import com.example.demo.repository.ChercheurRepository;
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
@RequestMapping("/api/v1")
public class ChercheurController {
    @Autowired
    private ChercheurRepository chercheurRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/chercheurs")
    public List<Chercheur> getAllChercheurs() {
        return chercheurRepository.findAll();
    }

    @GetMapping("/chercheurs/{id}")
    public ResponseEntity<Chercheur> getChercheurById(@PathVariable(value = "id") Long chercheurId)
            throws ResourceNotFoundException {
        Chercheur chercheur = chercheurRepository.findById(chercheurId)
                .orElseThrow(() -> new ResourceNotFoundException("Chercheur not found for this id :: " + chercheurId));
        return ResponseEntity.ok().body(chercheur);
    }

    @PostMapping("/chercheurs")
    public Chercheur createChercheur(@Valid @RequestBody Chercheur chercheur) {
        return chercheurRepository.save(chercheur);
    }

    @PostMapping("/chercheurs/{userId}")
    public ResponseEntity<Chercheur> createChercheur(@PathVariable(value = "userId") Long userId,
                                                 @RequestBody Chercheur chercheurRequest) throws ResourceNotFoundException {
        Chercheur chercheur = userRepository.findById(userId).map(user -> {
            chercheurRequest.setUser(user);
            //chercheurRequest.set
            return chercheurRepository.save(chercheurRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        return new ResponseEntity<>(chercheur, HttpStatus.CREATED);
    }

    @PutMapping("/chercheurs/{id}")
    public ResponseEntity<Chercheur> updateEmployee(@PathVariable(value = "id") Long chercheurId,
                                                   @Valid @RequestBody Chercheur chercheurDetails) throws ResourceNotFoundException {
        Chercheur chercheur = chercheurRepository.findById(chercheurId)
                .orElseThrow(() -> new ResourceNotFoundException("Chercheur not found for this id :: " + chercheurId));
        chercheur.setVille(chercheurDetails.getVille());
        chercheur.setDescription(chercheurDetails.getDescription());
        chercheur.setNaissance(chercheurDetails.getNaissance());
        final Chercheur updatedChercheur = chercheurRepository.save(chercheur);
        return ResponseEntity.ok(updatedChercheur);
    }

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