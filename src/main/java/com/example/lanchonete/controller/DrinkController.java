package com.example.lanchonete.controller;

import com.example.lanchonete.drink.Drink;
import com.example.lanchonete.drink.DrinkRepository;
import com.example.lanchonete.drink.DrinkRequestDTO;
import com.example.lanchonete.drink.DrinkResponseDTO;
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
        List<DrinkResponseDTO> drinklist = this.repository.findAll().stream().map(DrinkResponseDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(drinklist);
    }

    @PostMapping
    public ResponseEntity postDrink(@RequestBody DrinkRequestDTO data){
        Drink newDrink = new Drink(data);

        this.repository.save(newDrink);
        return ResponseEntity.status(HttpStatus.CREATED).body("Drink successfully registered!");
    }
}
