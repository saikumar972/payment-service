package com.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentExceptionHandler {
    @ExceptionHandler(PaymentErrorMessage.class)
    public PaymentErrorResponse getErrorMessage(PaymentErrorMessage paymentErrorMessage){
        return PaymentErrorResponse.builder()
                .errorMessage(paymentErrorMessage.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
