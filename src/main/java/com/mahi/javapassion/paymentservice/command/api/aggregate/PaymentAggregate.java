package com.mahi.javapassion.paymentservice.command.api.aggregate;

import com.mahi.javapassion.commonservice.command.ValidatePaymentCommand;
import com.mahi.javapassion.commonservice.event.PaymentProcessedEvent;
import com.mahi.javapassion.commonservice.model.CardDetails;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;
    private CardDetails cardDetails;

    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
        //Validate Payment Details
        //Publish the Payment processed event
        log.info("Executing Validate Payment Command for Order Id : {}, Payment Id : {}",
                validatePaymentCommand.getOrderId(), validatePaymentCommand.getPaymentId());
        PaymentProcessedEvent paymentProcessedEvent = PaymentProcessedEvent.builder()
                .paymentId(validatePaymentCommand.getPaymentId())
                .orderId(validatePaymentCommand.getOrderId())
                .build();
        AggregateLifecycle.apply(paymentProcessedEvent);
        log.info("Payment Processed Event Applied");
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        this.paymentId = paymentProcessedEvent.getPaymentId();
        this.orderId = paymentProcessedEvent.getOrderId();
    }
}
