package com.example.lanchonete.domain.order;

import com.example.lanchonete.domain.drink.Drink;
import com.example.lanchonete.domain.hamburger.Hamburger;
import com.example.lanchonete.domain.ingredient.FlagAdditional;
import com.example.lanchonete.domain.ingredient.Ingredient;
import com.example.lanchonete.requests.OrderRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "orders")
@Entity(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(length = 80, nullable = false)
    private String customer_name;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 100, nullable = false)
    private String street;

    @Column(length = 100, nullable = false)
    private String neighborhood;

    @Column(length = 9, nullable = false)
    private String zip_code;

    @Column(length = 100)
    private String complement;

    private Integer number;

    @Column(length = 2, nullable = false)
    private String state;

    @Column(length = 100, nullable = false)
    private String city;


    private Double total_price ;

    private String comments;

    @ManyToMany
    @JoinTable(
            name = "order_drinks",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "drinks_id")
    )
    List<Drink> drinks;

    @ManyToMany
    @JoinTable(
            name = "order_hamburgers",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "hamburgers_id")
    )
    List<Hamburger> hamburgers;

    @ManyToMany
    @JoinTable(
            name = "order_ingredients",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    List<Ingredient> ingredients;

    public Order(OrderRequestDTO data){
        this.date = LocalDateTime.now();
        this.customer_name = data.customer_name();
        this.phone = data.phone();
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.zip_code = data.zip_code();
        this.complement = data.complement();
        this.number = data.number();
        this.state = data.state();
        this.city = data.city();
        this.drinks = data.drinks();
        this.hamburgers = data.hamburgers();
        this.ingredients = data.ingredients();
        this.total_price = calculateTotalPrice();
        this.comments = data.comments();
    }

    @PrePersist
    private void validateFlagAdditional(){
        boolean flag = this.ingredients.stream().anyMatch(ingredient -> !ingredient.getFlag_additional().equals(FlagAdditional.ADDITIONAL));

        if(flag){
            throw new IllegalStateException("This additional is not avaliable to add");
        }
    }

    public Double calculateTotalPrice() {
        double total = 0;

        if (drinks != null) {
            for (Drink drink : drinks) {
                total += drink.getPrice();
            }
        }

        if (hamburgers == null || hamburgers.isEmpty()) {
            throw new IllegalStateException("Please, add at least one hamburger");
        } else {
            for (Hamburger hamburger : hamburgers) {
                total += hamburger.getPrice();
            }
        }

        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                total += ingredient.getPrice();
            }
        }
        return total;
    }

}
