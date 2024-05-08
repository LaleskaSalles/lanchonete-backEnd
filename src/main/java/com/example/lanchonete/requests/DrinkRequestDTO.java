package com.example.lanchonete.requests;

import com.example.lanchonete.domain.drink.FlagSugar;

public record DrinkRequestDTO(String name, String description, Double price, FlagSugar flag_sugar) {
}
