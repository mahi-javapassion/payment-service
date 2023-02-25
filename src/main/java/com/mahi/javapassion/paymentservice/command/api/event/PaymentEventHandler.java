package com.mahi.javapassion.paymentservice.command.api.event;

import com.mahi.javapassion.commonservice.event.PaymentProcessedEvent;
import com.mahi.javapassion.paymentservice.command.api.entity.PaymentEntity;
import com.mahi.javapassion.paymentservice.command.api.repository.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PaymentEventHandler {

    private PaymentRepository paymentRepository;

    public PaymentEventHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        PaymentEntity entity = PaymentEntity.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("COMPLETED")
                .timeStamp(LocalDate.now())
                .build();
        paymentRepository.save(entity);
    }
}
