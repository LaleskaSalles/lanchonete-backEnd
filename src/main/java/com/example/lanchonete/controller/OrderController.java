package com.example.lanchonete.controller;

import com.example.lanchonete.order.Order;
import com.example.lanchonete.order.OrderRepository;
import com.example.lanchonete.order.OrderRequestDTO;
import com.example.lanchonete.order.OrderResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @GetMapping
    public ResponseEntity getAllOrders() {
        try {
            List<OrderResponseDTO> orderList = this.repository.findAll().stream().map(OrderResponseDTO::new).toList();

            return ResponseEntity.status(HttpStatus.OK).body(orderList);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrderById(@PathVariable Long id) {
        try {
            Optional<Order> optionalOrder = repository.findById(id);

            if (optionalOrder.isPresent()) {
                OrderResponseDTO orderResponseDTO = new OrderResponseDTO(optionalOrder.get());
                return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity postOrder(@RequestBody OrderRequestDTO data) {
        try {
            Order newOrder = new Order(data);

            this.repository.save(newOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order succesfully registered");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
