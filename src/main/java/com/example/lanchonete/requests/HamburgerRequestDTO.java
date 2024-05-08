package com.example.lanchonete.requests;

import com.example.lanchonete.domain.ingredient.Ingredient;

import java.util.List;

public record HamburgerRequestDTO(
        String name,
        String description,
        Double price,
        List<Ingredient> ingredients
        ) {
}


