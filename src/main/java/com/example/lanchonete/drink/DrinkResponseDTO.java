package com.example.lanchonete.drink;

public record DrinkResponseDTO(Long id, String name, String description, Double price, FlagSugar flag_sugar) {
    public DrinkResponseDTO(Drink drink){
        this(drink.getId(), drink.getName(), drink.getDescription(), drink.getPrice(), drink.getFlag_sugar());
    }

}
