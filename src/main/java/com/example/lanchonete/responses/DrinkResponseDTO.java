package com.example.lanchonete.responses;

import com.example.lanchonete.domain.drink.Drink;
import com.example.lanchonete.domain.drink.FlagSugar;

public record DrinkResponseDTO(Long id, String name, String description, Double price, FlagSugar flag_sugar) {
    public DrinkResponseDTO(Drink drink){
        this(drink.getId(), drink.getName(), drink.getDescription(), drink.getPrice(), drink.getFlag_sugar());
    }

}
