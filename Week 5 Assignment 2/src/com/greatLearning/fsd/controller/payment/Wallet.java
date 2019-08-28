package com.greatLearning.fsd.controller.payment;

public class Wallet implements PaymentGateway {
    private final String type;

    public Wallet() {
        type = "Wallet";
    }

    public String getType() {
        return type;
    }

    public boolean processPayment(int amount) {
        System.out.println("Processing payment by Wallet...");
        System.out.println("Payment of " + amount + " is Successfull !!!");

        return true;
    }
}