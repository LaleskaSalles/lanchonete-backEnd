package com.example.lanchonete.repositories;

import com.example.lanchonete.domain.drink.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
