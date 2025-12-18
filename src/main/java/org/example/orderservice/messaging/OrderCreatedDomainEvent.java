package org.example.orderservice.messaging;

import org.example.orderservice.model.Order;

public record OrderCreatedDomainEvent(Order order) {}
