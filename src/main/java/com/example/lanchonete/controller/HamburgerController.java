package com.example.lanchonete.controller;

import com.example.lanchonete.hamburger.Hamburger;
import com.example.lanchonete.hamburger.HamburgerRepository;
import com.example.lanchonete.hamburger.HamburgerRequestDTO;
import com.example.lanchonete.hamburger.HamburgerResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("hamburger")
public class HamburgerController {

    @Autowired
    private HamburgerRepository repository;

    @GetMapping
    public ResponseEntity getAllHamburgers() {
        try {
            List<HamburgerResponseDTO> hamburgerList = this.repository.findAll().stream().map(HamburgerResponseDTO::new).toList();
            return ResponseEntity.status(HttpStatus.OK).body(hamburgerList);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity postHamburger(@RequestBody HamburgerRequestDTO data) {
        try {
            Hamburger newHamburger = new Hamburger(data);

            this.repository.save(newHamburger);
            return ResponseEntity.status(HttpStatus.CREATED).body("Hamburger succesfully registered");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putHamburger(@PathVariable Long id, @RequestBody HamburgerRequestDTO data) {
        try {
            Hamburger hamburger = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Hamburger not found"));


            hamburger.updateData(data);
            repository.save(hamburger);

            return ResponseEntity.status(HttpStatus.OK).body("Hamburger succesfully updated");

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHamburger(@PathVariable Long id) {
        try {
            Hamburger hamburger = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Hamburger not found"));
            repository.delete(hamburger);

            return ResponseEntity.status(HttpStatus.OK).body("Hamburger deleted!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
