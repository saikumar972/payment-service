package com.payment.exception;

public class PaymentErrorMessage extends RuntimeException{
    public PaymentErrorMessage(String message){
        super(message);
    }
}
