package com.greatLearning.fsd.controller.payment;

public class NetBanking implements PaymentGateway {
    private final String type;

    public NetBanking() {
        type = "Net Banking";
    }

    public String getType() {
        return type;
    }

    public boolean processPayment(int amount) {
        System.out.println("Processing payment by Net Banking...");
        System.out.println("Payment of " + amount + " is Successfull !!!");

        return true;
    }
}