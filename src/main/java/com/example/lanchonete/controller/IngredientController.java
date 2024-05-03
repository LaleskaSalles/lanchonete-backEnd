package com.example.lanchonete.controller;


import com.example.lanchonete.ingredient.Ingredient;
import com.example.lanchonete.ingredient.IngredientRepository;
import com.example.lanchonete.ingredient.IngredientRequestDTO;
import com.example.lanchonete.ingredient.IngredientResponseDTO;
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
        List<IngredientResponseDTO> ingredientList = this.repository.findAll().stream().map(IngredientResponseDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(ingredientList);
    }

    @PostMapping
    public ResponseEntity postIngredient(@RequestBody IngredientRequestDTO data) {
        Ingredient newIngredient = new Ingredient(data);
        this.repository.save(newIngredient);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ingredient succesfully registered");
    }
}
