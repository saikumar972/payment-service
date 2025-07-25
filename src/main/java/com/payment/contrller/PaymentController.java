package com.payment.contrller;

import com.payment.entity.Payment;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/add")
    public ResponseEntity<List<Payment>> addPayments(@RequestBody List<Payment> paymentList){
        List<Payment> paymentList1=paymentService.addPayments(paymentList);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentList1);
    }

    @PutMapping("/option/{paymentMode}/{amount}")
    public ResponseEntity<String> findByPaymentMode(@PathVariable String paymentMode,@PathVariable double amount){
        String paymentStatus=paymentService.getPaymentStatus(paymentMode,amount);
        return ResponseEntity.status(HttpStatus.OK).body(paymentStatus);
    }

    @GetMapping("/all")
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }
}
