package com.example.lanchonete.controller;

import com.example.lanchonete.domain.ingredient.Ingredient;
import com.example.lanchonete.repositories.IngredientRepository;
import com.example.lanchonete.requests.IngredientRequestDTO;
import com.example.lanchonete.responses.IngredientResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity getAllIngredients() {
        try {
            List<IngredientResponseDTO> ingredientList = this.repository.findAll().stream().map(IngredientResponseDTO::new).toList();

            return ResponseEntity.status(HttpStatus.OK).body(ingredientList);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity getIngredientById(@PathVariable Long id) {
        try {
            Optional<Ingredient> ingredient = this.repository.findById(id);

            if (ingredient.isPresent()) {
                IngredientResponseDTO IngredientResponseDTO = new IngredientResponseDTO(ingredient.get());
                return ResponseEntity.status(HttpStatus.OK).body(IngredientResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingredient not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity postIngredient(@RequestBody IngredientRequestDTO data) {
        try {
            Ingredient newIngredient = new Ingredient(data);

            this.repository.save(newIngredient);
            IngredientResponseDTO ingredientResponseDTO  = new IngredientResponseDTO(newIngredient);
            return ResponseEntity.status(HttpStatus.CREATED).body(ingredientResponseDTO);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity putIngredient(@PathVariable Long id, @RequestBody IngredientRequestDTO data) {
        try {
            Ingredient updateIngredient = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

            updateIngredient.updateData(data);
            repository.save(updateIngredient);
            IngredientResponseDTO ingredientResponseDTO  = new IngredientResponseDTO(updateIngredient);
            return ResponseEntity.status(HttpStatus.OK).body(ingredientResponseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteIngredient(@PathVariable Long id) {
        try {
            Ingredient deleteIngredient = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

            repository.delete(deleteIngredient);
            IngredientResponseDTO ingredientResponseDTO  = new IngredientResponseDTO(deleteIngredient);
            return ResponseEntity.status(HttpStatus.OK).body(ingredientResponseDTO);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
