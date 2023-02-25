package com.mahi.javapassion.paymentservice.command.api.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYMENTS")
public class PaymentEntity {
    @Id
    private String paymentId;
    private String orderId;
    private LocalDate timeStamp;
    private String paymentStatus;
}
