package com.example.lanchonete.requests;

import com.example.lanchonete.domain.ingredient.FlagAdditional;

public record IngredientRequestDTO(String name, String description, Double price, FlagAdditional flag_additional) {
}
