package com.greatLearning.fsd.controller.payment;

public class DebitCard implements PaymentGateway {
    private final String type;

    public DebitCard() {
        type = "Debit Card";
    }
    
    public String getType() {
        return type;
    }

    public boolean processPayment(int amount) {
        System.out.println("Processing payment by Debit card...");
        System.out.println("Payment of " + amount + " is Successfull !!!");

        return true;
    }
}