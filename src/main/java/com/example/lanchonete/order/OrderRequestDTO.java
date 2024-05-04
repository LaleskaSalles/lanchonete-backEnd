package com.example.lanchonete.order;

import com.example.lanchonete.drink.Drink;
import com.example.lanchonete.hamburger.Hamburger;
import com.example.lanchonete.ingredient.Ingredient;

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
        List<Drink> drinks,
        List<Hamburger> hamburgers,
        List<Ingredient> ingredients) {
}



