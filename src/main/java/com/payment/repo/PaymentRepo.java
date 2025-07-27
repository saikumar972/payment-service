package com.payment.repo;

import com.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
    Optional<Payment> findByPaymentMode(String paymentMode);

    @Query(value = "select amount from payment where payment_Mode =:paymentMode",nativeQuery = true)
    Optional<Double> getAmountByPaymentSource(@Param("paymentMode") String paymentMode);

    @Query(value = "select * from payment where payment_Mode =:paymentMode",nativeQuery = true)
    Optional<Payment> getPaymentByPaymentSource(@Param("paymentMode") String paymentMode);
}
