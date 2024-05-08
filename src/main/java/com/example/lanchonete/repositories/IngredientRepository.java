package com.example.lanchonete.repositories;

import com.example.lanchonete.domain.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
