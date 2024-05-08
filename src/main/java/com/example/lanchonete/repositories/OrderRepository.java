package com.example.lanchonete.repositories;

import com.example.lanchonete.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
