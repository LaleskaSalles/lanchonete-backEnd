package com.example.lanchonete.responses;

import com.example.lanchonete.domain.hamburger.Hamburger;

public record HamburgerResponseDTO(Long id, String name, String description, Double price){
    public HamburgerResponseDTO(Hamburger hamburger){
        this(hamburger.getId(), hamburger.getName(), hamburger.getDescription(), hamburger.getPrice());
    }
}
