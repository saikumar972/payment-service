package com.payment.service;

import com.payment.entity.Payment;
import com.payment.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    public PaymentRepo paymentRepo;

    public List<Payment> addPayments(List<Payment> payments){
        return paymentRepo.saveAll(payments);
    }

    public String getPaymentStatus(String paymentMode,double purchaseAmount){
        double availableAmount= paymentRepo.getAmountByPaymentSource(paymentMode);
        if(availableAmount<purchaseAmount){
            throw new IllegalArgumentException("Entered amount is exceeded the available limit");
        }else{
            Payment payment = paymentRepo.findByPaymentMode(paymentMode)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid payment mode: " + paymentMode));            payment.setAmount(availableAmount-purchaseAmount);
            paymentRepo.save(payment);
        }
        return "payment success";
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }
}
