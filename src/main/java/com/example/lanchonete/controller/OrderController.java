package com.example.lanchonete.controller;

import com.example.lanchonete.domain.order.Order;
import com.example.lanchonete.repositories.OrderRepository;
import com.example.lanchonete.requests.OrderRequestDTO;
import com.example.lanchonete.responses.OrderResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity getOrderById(@PathVariable Long id) {
        try {
            Optional<Order> order = repository.findById(id);

            if (order.isPresent()) {
                OrderResponseDTO orderResponseDTO = new OrderResponseDTO(order.get());
                return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity postOrder(@RequestBody OrderRequestDTO data) {
        try {
            Order newOrder = new Order(data);

            this.repository.save(newOrder);
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO(newOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
