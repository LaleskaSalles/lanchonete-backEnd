package com.example.lanchonete.order;

import com.example.lanchonete.drink.Drink;
import com.example.lanchonete.hamburger.HamburgerResponseDTO;
import com.example.lanchonete.ingredient.Ingredient;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        LocalDateTime date,
        String customer_name,
        String phone,
        String street,
        String neighborhood,
        String zip_code,
        String complement,
        Integer number,
        String state,
        String city,
        Double total_price,
        List<Drink> drinks,
        List<HamburgerResponseDTO> hamburgers,
        List<Ingredient> ingredients
) {
    public OrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getDate(),
                order.getCustomer_name(),
                order.getPhone(),
                order.getStreet(),
                order.getNeighborhood(),
                order.getZip_code(),
                order.getComplement(),
                order.getNumber(),
                order.getState(),
                order.getCity(),
                order.getTotal_price(),
                order.getDrinks(),
                order.getHamburgers().stream().map(HamburgerResponseDTO::new).toList(),
                order.getIngredients()
                );
    }
}
