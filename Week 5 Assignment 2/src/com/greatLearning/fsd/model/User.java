package com.greatLearning.fsd.model;

import com.greatLearning.fsd.controller.payment.CreditCard;
import com.greatLearning.fsd.controller.payment.DebitCard;
import com.greatLearning.fsd.controller.payment.NetBanking;
import com.greatLearning.fsd.controller.payment.PaymentGateway;
import com.greatLearning.fsd.controller.payment.Wallet;
import com.greatLearning.fsd.controller.shoppingcart.ShoppingCart;

public class User extends Account {
    private ShoppingCart shoppingCart;
    private PaymentGateway debitCard;
    private PaymentGateway creditCard;
    private PaymentGateway netBanking;
    private PaymentGateway wallet;

    public User(String name, Address address, String contact) {
        super(name, address, contact);

        shoppingCart = new ShoppingCart();
        debitCard = new DebitCard();
        creditCard = new CreditCard();
        netBanking = new NetBanking();
        wallet = new Wallet();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public PaymentGateway selectDebitCard() {
        return debitCard;
    }

    public PaymentGateway selectCreditCard() {
        return creditCard;
    }

    public PaymentGateway selectNetBanking() {
        return netBanking;
    }

    public PaymentGateway selectWallet() {
        return wallet;
    }

    public void addToCart(Item item) {
        shoppingCart.addItem(item);
    }

    public void removeFromCart(Item item) {
        shoppingCart.removeItem(item);
    }

    public boolean checkout(PaymentGateway paymentGateway) {
        if(shoppingCart.isEmpty()) {
            System.out.println("Your cart is empty !!!");
            return false;
        }

        boolean paymentSuccessfull = paymentGateway.processPayment(shoppingCart.getTotalAmount());
        if(paymentSuccessfull == true) {
            return true;
        }
        else {
            System.out.println("Checkout failed !!!");
            return false;
        }
    }
    
	@Override
	public int compareTo(Account other) {
		return super.getName().compareTo(other.getName());
	}
}