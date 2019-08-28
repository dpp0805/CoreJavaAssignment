package com.greatLearning.fsd.controller.payment;

public interface PaymentGateway {
    boolean processPayment(int amount);
}