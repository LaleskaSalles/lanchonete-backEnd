package com.example.lanchonete.hamburger;

import com.example.lanchonete.ingredient.Ingredient;
import com.example.lanchonete.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    List<Ingredient> ingredients;

    @ManyToMany(mappedBy = "hamburgers")
    @JsonIgnore
    List<Order> hamburgerOrders;

    public Hamburger(HamburgerRequestDTO data){
        this.name = data.name().toUpperCase();
        this.description = data.description().toUpperCase();
        this.price = data.price();
        this.ingredients = data.ingredients();
    }


    public void updateData(HamburgerRequestDTO data) {
        if (data.name() != null && data.price() != null && data.ingredients() != null) {
            this.name = data.name().toUpperCase();
            this.description = data.description().toUpperCase();
            this.price = data.price();
            this.ingredients = data.ingredients();
        }
    }

    @PrePersist
    private void validatePrice(){
        if (this.price <= 0){
            throw new IllegalStateException("Price must be greater than 0");
        }
    }
}
