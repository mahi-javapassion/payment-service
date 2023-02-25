package com.mahi.javapassion.paymentservice.command.api.repository;

import com.mahi.javapassion.paymentservice.command.api.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {

}
