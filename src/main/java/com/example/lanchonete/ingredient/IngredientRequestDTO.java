package com.example.lanchonete.ingredient;

public record IngredientRequestDTO(String name, String description, Double price, FlagAdditional flag_additional) {
}
