package com.example.lanchonete.controller;

import com.example.lanchonete.drink.Drink;
import com.example.lanchonete.drink.DrinkResponseDTO;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity getHamburgerById(@PathVariable Long id) {
        try {
            Optional<Hamburger> hamburger = this.repository.findById(id);

            if (hamburger.isPresent()) {
                HamburgerResponseDTO hamburgerResponseDTO = new HamburgerResponseDTO(hamburger.get());
                return ResponseEntity.status(HttpStatus.OK).body(hamburgerResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hamburger not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity postHamburger(@RequestBody HamburgerRequestDTO data) {
        try {
            Hamburger newHamburger = new Hamburger(data);

            this.repository.save(newHamburger);
            HamburgerResponseDTO responseDTO = new HamburgerResponseDTO(newHamburger);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity putHamburger(@PathVariable Long id, @RequestBody HamburgerRequestDTO data) {
        try {
            Hamburger updateHamburger = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Hamburger not found"));


            updateHamburger.updateData(data);
            repository.save(updateHamburger);
            HamburgerResponseDTO responseDTO = new HamburgerResponseDTO(updateHamburger);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteHamburger(@PathVariable Long id) {
        try {
            Hamburger deleteHamburger = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Hamburger not found"));


            repository.delete(deleteHamburger);

            HamburgerResponseDTO responseDTO = new HamburgerResponseDTO(deleteHamburger);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
