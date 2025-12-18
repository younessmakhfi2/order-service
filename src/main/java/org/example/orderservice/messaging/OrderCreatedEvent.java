package org.example.orderservice.messaging;


import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        String productCode,
        Integer quantity,
        Instant createdAt
) {}
