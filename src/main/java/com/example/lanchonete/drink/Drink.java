package com.example.lanchonete.drink;

import com.example.lanchonete.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "drinks")
@Entity(name = "drinks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlagSugar flag_sugar;

    @ManyToMany(mappedBy = "drinks")
    @JsonIgnore
    List<Order> drinksOrders;

    public Drink(DrinkRequestDTO data){
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.flag_sugar = data.flag_sugar();
    }

    public void updateData(DrinkRequestDTO data) {
        if (data.name() !=null && data.price() !=null && data.flag_sugar() !=null){
            this.name = data.name();
            this.description = data.description();
            this.price = data.price();
            this.flag_sugar = data.flag_sugar();
        }
    }

    @PrePersist
    private void validatePrice(){
        if (this.price <= 0){
            throw new IllegalStateException("Price must be greater than 0");
        }
    }
}
