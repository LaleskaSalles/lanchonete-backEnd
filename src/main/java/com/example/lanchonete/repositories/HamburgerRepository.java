package com.example.lanchonete.repositories;

import com.example.lanchonete.domain.hamburger.Hamburger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HamburgerRepository extends JpaRepository<Hamburger, Long> {
}
