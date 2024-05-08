package com.example.lanchonete.responses;

import com.example.lanchonete.domain.ingredient.FlagAdditional;
import com.example.lanchonete.domain.ingredient.Ingredient;

public record IngredientResponseDTO(Long id, String name, String description, Double price, FlagAdditional flag_additional) {
    public IngredientResponseDTO(Ingredient ingredient) {
        this(ingredient.getId(), ingredient.getName(), ingredient.getDescription(), ingredient.getPrice(), ingredient.getFlag_additional());
    }
}
