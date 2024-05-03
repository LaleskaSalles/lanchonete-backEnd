package com.example.lanchonete.hamburger;

import com.example.lanchonete.ingredient.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "hamburgers")
@Entity(name = "hamburgers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Hamburger {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "hamburgers_ingredients",
            joinColumns = @JoinColumn(name = "hamburgers_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    List<Ingredient> ingredients;

    public Hamburger(HamburgerRequestDTO data){
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.ingredients = data.ingredients();
    }


    public void updateData(HamburgerRequestDTO data) {
        if (data.name() != null || data.price() != null || data.ingredients() != null) {
            this.name = data.name();
            this.description = data.description();
            this.price = data.price();
            this.ingredients = data.ingredients();
        }
    }
}
