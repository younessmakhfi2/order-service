package org.example.orderservice.listener;

import org.example.orderservice.messaging.OrderCreatedDomainEvent;
import org.example.orderservice.messaging.OrderCreatedEvent;
import org.example.orderservice.messaging.OrderEventPublisher;
import org.example.orderservice.model.Order;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderCreatedEventListener {

    private final OrderEventPublisher publisher;

    public OrderCreatedEventListener(OrderEventPublisher publisher) {
        this.publisher = publisher;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(OrderCreatedDomainEvent event) {

        Order o = event.order();

        OrderCreatedEvent kafkaEvent = new OrderCreatedEvent(
                o.getId(),
                o.getProductCode(),
                o.getQuantity(),
                o.getCreatedAt()
        );

        publisher.publish(kafkaEvent);
    }
}