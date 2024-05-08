package com.example.lanchonete.controller;

import com.example.lanchonete.domain.drink.Drink;
import com.example.lanchonete.domain.ingredient.Ingredient;
import com.example.lanchonete.repositories.DrinkRepository;
import com.example.lanchonete.requests.DrinkRequestDTO;
import com.example.lanchonete.requests.IngredientRequestDTO;
import com.example.lanchonete.responses.DrinkResponseDTO;
import com.example.lanchonete.responses.IngredientResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/drink")
public class DrinkController {

    @Autowired
    private DrinkRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity getAllDrinks() {
        try {
            List<DrinkResponseDTO> drinklist = this.repository.findAll().stream().map(DrinkResponseDTO::new).toList();
            return ResponseEntity.status(HttpStatus.OK).body(drinklist);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity getDrinkById(@PathVariable Long id) {
        try {
            Optional<Drink> drink = this.repository.findById(id);

            if (drink.isPresent()) {
                DrinkResponseDTO drinkResponseDTO = new DrinkResponseDTO(drink.get());
                return ResponseEntity.status(HttpStatus.OK).body(drinkResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Drink not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity postDrink(@RequestBody DrinkRequestDTO data) {
        try {
            Drink newDrink = new Drink(data);

            this.repository.save(newDrink);
            DrinkResponseDTO drinkResponseDTO  = new DrinkResponseDTO(newDrink);
            return ResponseEntity.status(HttpStatus.CREATED).body(drinkResponseDTO);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity putDrink(@PathVariable Long id, @RequestBody DrinkRequestDTO data) {
        try {

            Drink updateDrink = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Drink not found"));

            updateDrink.updateData(data);
            repository.save(updateDrink);
            DrinkResponseDTO responseDTO = new DrinkResponseDTO(updateDrink);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDrink(@PathVariable Long id) {
        try {
            Drink delelteDrink = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Drink not found"));
            repository.delete(delelteDrink);

            DrinkResponseDTO responseDTO = new DrinkResponseDTO(delelteDrink);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
