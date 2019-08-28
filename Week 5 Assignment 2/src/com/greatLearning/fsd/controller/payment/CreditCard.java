package com.greatLearning.fsd.controller.payment;

public class CreditCard implements PaymentGateway {
    private final String type;

    public CreditCard() {
        type = "Credit Card";
    }

    public String getType() {
        return type;
    }

    public boolean processPayment(int amount) {
        System.out.println("Processing payment by Credit card...");
        System.out.println("Payment of " + amount + " is Successfull !!!");

        return true;
    }
}