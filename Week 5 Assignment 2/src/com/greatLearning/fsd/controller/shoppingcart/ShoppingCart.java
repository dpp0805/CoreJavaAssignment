package com.greatLearning.fsd.controller.shoppingcart;

import java.util.List;
import java.util.ArrayList;
import com.greatLearning.fsd.model.Item;

public class ShoppingCart {
    private final List<Item> items =  new ArrayList<>();
    private int totalAmount;

    public List<Item> getItems() {
        return items;
    }
    
    public int getTotalAmount() {
        return totalAmount;
    }

    public void addItem(Item item) {
        items.add(item);
        totalAmount += item.getProduct().getPrice() * item.getQuantity();
    }
    public void removeItem(Item item) {
        items.remove(item);
        totalAmount -= item.getProduct().getPrice();
    }
 
    public boolean isEmpty() {
    	return items.isEmpty();
    }
    
    public void clearCart(){
        items.clear();
        totalAmount = 0;
    }
}