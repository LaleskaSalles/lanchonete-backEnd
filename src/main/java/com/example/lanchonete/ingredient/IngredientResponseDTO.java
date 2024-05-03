package com.example.lanchonete.ingredient;

public record IngredientResponseDTO(Long id, String name, String description, Double price, FlagAdditional flag_additional) {
    public IngredientResponseDTO(Ingredient ingredient) {
        this(ingredient.getId(), ingredient.getName(), ingredient.getDescription(), ingredient.getPrice(), ingredient.getFlag_additional());
    }
}
