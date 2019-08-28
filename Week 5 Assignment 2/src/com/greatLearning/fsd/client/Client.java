package com.greatLearning.fsd.client;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.greatLearning.fsd.controller.category.Fashion;
import com.greatLearning.fsd.controller.category.Mobiles;
import com.greatLearning.fsd.controller.payment.PaymentGateway;
import com.greatLearning.fsd.model.Address;
import com.greatLearning.fsd.model.Item;
import com.greatLearning.fsd.model.Product;
import com.greatLearning.fsd.model.Seller;
import com.greatLearning.fsd.model.User;

public class Client {
    public static void main(String args[]) {
    	Client client = new Client();
    	
        User user_1 = new User("Preetom", new Address(203, "JPC Pooja", "Sarjapura", "Bangalore"), "9435333372");
        Seller seller_1 = new Seller("Rohan", new Address(102, "DX Max", "Hennur", "Pune"), "9876554422");
        Seller seller_2 = new Seller("Vijay", new Address(342, "Maruti Krupa", "Palawali", "Mumbai"), "9872443221");

        client.execute(seller_1, seller_2, user_1);
    }

    private void execute(Seller seller_1, Seller seller_2, User user_1) {
        //Available products
        Product product_1 = new Product("iPhone 5s", new Mobiles(), 35000);
        Product product_2 = new Product("Redmi Note 3", new Mobiles(), 20000);
        Product product_3 = new Product("Jeans Pant", new Fashion(), 3000);
        Product product_4 = new Product("Puma Sneakers", new Fashion(), 2500);
        Product product_5 = new Product("T-Shirt", new Fashion(), 1000);

        //Setup Inventory for the seller_1
        Item item1 = new Item(product_1, seller_1, 2);
        Item item2 = new Item(product_2, seller_1, 3);
        Item item3 = new Item(product_3, seller_1, 5);
        Item item4 = new Item(product_4, seller_1, 5);
        Item item5 = new Item(product_5, seller_1, 10);
        seller_1.setupInventory(Arrays.asList(item1, item2, item3, item4, item5)); 
        //Setup Inventory for the seller_2
        Item item6 = new Item(product_1, seller_2, 1);
        Item item7 = new Item(product_2, seller_2, 2);
        Item item8 = new Item(product_3, seller_2, 10);
        Item item9 = new Item(product_4, seller_2, 4);
        Item item10 = new Item(product_5, seller_2, 2);
        seller_2.setupInventory(Arrays.asList(item6, item7, item8, item9, item10));

        //Shop
        user_1.addToCart(new Item(product_1, seller_1, 1));
        user_1.addToCart(new Item(product_3, seller_2, 2));
        user_1.addToCart(new Item(product_4, seller_2, 1));

        //Proceed Checkout
        PaymentGateway paymentGateway = user_1.selectNetBanking();
        if(user_1.checkout(paymentGateway) == true) {
        	generateBill(user_1);
            List<Item> orderedItems = user_1.getShoppingCart().getItems();

            seller_1.updateInventory(orderedItems);
            seller_2.updateInventory(orderedItems);

            user_1.getShoppingCart().clearCart();
        }
    }
    
    private void generateBill(User user) {
        System.out.println("*** Bill ***");
        List<Item> orderedItems = user.getShoppingCart().getItems();
        
        Iterator<Item> it = orderedItems.iterator();
        while(it.hasNext()) {
        	Item item = it.next();
        	String productName = item.getProduct().getName();
            int quantity = item.getQuantity();
            String sellerName = item.getSeller().getName();
            System.out.println("Item: " + productName + "\nQuantity: " + quantity + "\nSeller: " + sellerName);   
        }
        System.out.println("Order Id: 123");
        System.out.println("Amount Paid: " + user.getShoppingCart().getTotalAmount());
    }
}