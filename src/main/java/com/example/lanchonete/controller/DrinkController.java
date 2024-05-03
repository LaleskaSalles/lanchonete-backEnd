package com.example.lanchonete.controller;

import com.example.lanchonete.drink.Drink;
import com.example.lanchonete.drink.DrinkRepository;
import com.example.lanchonete.drink.DrinkRequestDTO;
import com.example.lanchonete.drink.DrinkResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("drink")
public class DrinkController {

    @Autowired
    private DrinkRepository repository;

    @GetMapping
    public ResponseEntity getAllDrinks() {
        try {
            List<DrinkResponseDTO> drinklist = this.repository.findAll().stream().map(DrinkResponseDTO::new).toList();
            return ResponseEntity.status(HttpStatus.OK).body(drinklist);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity postDrink(@RequestBody DrinkRequestDTO data) {
        try {
            Drink newDrink = new Drink(data);

            this.repository.save(newDrink);
            return ResponseEntity.status(HttpStatus.CREATED).body("Drink successfully registered!");
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putDrink(@PathVariable Long id, @RequestBody DrinkRequestDTO data) {
        try {

            Drink drink = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Drink not found"));

            drink.updateData(data);
            repository.save(drink);

            return ResponseEntity.status(HttpStatus.OK).body("Drink successfully updated!");
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDrink(@PathVariable Long id) {
        try {
            Drink drink = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Drink not found"));
            repository.delete(drink);

            return ResponseEntity.status(HttpStatus.OK).body("Drink deleted!");
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
