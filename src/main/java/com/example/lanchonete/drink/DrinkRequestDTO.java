package com.example.lanchonete.drink;

public record DrinkRequestDTO(String name, String description, Double price, FlagSugar flag_sugar) {
}
