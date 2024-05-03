package com.example.lanchonete.controller;

import com.example.lanchonete.ingredient.Ingredient;
import com.example.lanchonete.ingredient.IngredientRepository;
import com.example.lanchonete.ingredient.IngredientRequestDTO;
import com.example.lanchonete.ingredient.IngredientResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @GetMapping
    public ResponseEntity getAllIngredients() {
        try {
            List<IngredientResponseDTO> ingredientList = this.repository.findAll().stream().map(IngredientResponseDTO::new).toList();

            return ResponseEntity.status(HttpStatus.OK).body(ingredientList);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity postIngredient(@RequestBody IngredientRequestDTO data) {
        try {
            Ingredient newIngredient = new Ingredient(data);

            this.repository.save(newIngredient);
            return ResponseEntity.status(HttpStatus.CREATED).body("Ingredient succesfully registered");

        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putIngredient(@PathVariable Long id, @RequestBody IngredientRequestDTO data) {
        try {
            Ingredient ingredient = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

            ingredient.updateData(data);
            repository.save(ingredient);

            return ResponseEntity.status(HttpStatus.OK).body("Ingredient successfully updated");
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteIngredient(@PathVariable Long id) {
        try {
            Ingredient ingredient = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

            repository.delete(ingredient);
            return ResponseEntity.status(HttpStatus.OK).body("Ingredient deleted!");

        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
