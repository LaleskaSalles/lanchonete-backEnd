package com.example.lanchonete.ingredient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "ingredients")
@Entity(name = "ingredients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100, nullable = true)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlagAdditional flag_additional;

    public Ingredient(IngredientRequestDTO data){
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.flag_additional = data.flag_additional();
    }
}
