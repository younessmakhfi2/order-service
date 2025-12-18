package org.example.orderservice.service;

import org.example.orderservice.messaging.OrderCreatedDomainEvent;
import org.example.orderservice.messaging.OrderCreatedEvent;
import org.example.orderservice.model.Order;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public OrderService(OrderRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Order createOrder(Order order) {
        order.setCreatedAt(Instant.now());
        //        eventPublisher.publishEvent(
//                new OrderCreatedDomainEvent(savedOrder)
//        );
        return repository.save(order);
    }

    @Cacheable(value = "orders", key = "#id")
    public Order getOrder(UUID id) {
        System.out.println("Fetching order from DB: " + id); // <- log
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
