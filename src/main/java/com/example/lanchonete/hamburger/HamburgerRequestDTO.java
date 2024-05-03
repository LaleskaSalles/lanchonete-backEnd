package com.example.lanchonete.hamburger;

import com.example.lanchonete.ingredient.Ingredient;

import java.util.List;

public record HamburgerRequestDTO(
        String name,
        String description,
        Double price,
        List<Ingredient> ingredients
        ) {
}


