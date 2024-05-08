package com.example.lanchonete.requests;

import com.example.lanchonete.domain.drink.Drink;
import com.example.lanchonete.domain.hamburger.Hamburger;
import com.example.lanchonete.domain.ingredient.Ingredient;

import java.util.List;

public record OrderRequestDTO(
        String customer_name,
        String phone,
        String street,
        String neighborhood,
        String zip_code,
        String complement,
        Integer number,
        String state,
        String city,
        String comments,
        List<Drink> drinks,
        List<Hamburger> hamburgers,
        List<Ingredient> ingredients) {
}



