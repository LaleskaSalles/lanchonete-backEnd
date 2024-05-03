package com.example.lanchonete.drink;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(length = 100, nullable = true)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlagSugar flag_sugar;

    public Drink(DrinkRequestDTO data){
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.flag_sugar = data.flag_sugar();
    }

    public void updateData(DrinkRequestDTO data) {
        if (data.name() !=null || data.price() !=null || data.flag_sugar() !=null){
            this.name = data.name();
            this.description = data.description();
            this.price = data.price();
            this.flag_sugar = data.flag_sugar();
        }
    }
}
