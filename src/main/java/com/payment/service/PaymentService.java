package com.payment.service;

import com.payment.entity.Payment;
import com.payment.exception.PaymentErrorMessage;
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

        Payment payment= paymentRepo.getPaymentByPaymentSource(paymentMode).orElseThrow(()->new PaymentErrorMessage(paymentMode+" is Invalid payment option please chose upi/cash/netBanking"));
        double availableAmount=payment.getAmount();
        if(availableAmount<purchaseAmount){
            throw new PaymentErrorMessage("Entered amount "+purchaseAmount+" is exceeded the available limit");
        }else{
            payment.setAmount(availableAmount-purchaseAmount);
            paymentRepo.save(payment);
        }
        return "payment success";
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }
}
