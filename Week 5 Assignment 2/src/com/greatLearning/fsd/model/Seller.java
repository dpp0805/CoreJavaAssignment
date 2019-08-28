package com.greatLearning.fsd.model;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Seller extends Account {
    private final List<Item> inventory = new ArrayList<>();

    public Seller(String name, Address address, String contact) {
        super(name, address, contact);
    }

    public void setupInventory(List<Item> items) {
        inventory.addAll(items);
    }
    public void updateInventory(List<Item> items) {
    	Iterator<Item> it = items.iterator();
    	while(it.hasNext()) {
    		Item orderedItem = it.next();
    		Item inventoryItem = items.stream()
    								   .filter(item -> item.equals(orderedItem))
    								   .findFirst().get();
    												
    		inventoryItem.updateQuantity(orderedItem.getQuantity());
    		//Remove item from inventory when the quantity is 0
    		if(inventoryItem.getQuantity() == 0) {
    			inventory.remove(orderedItem);
            }
    	}
    }

	@Override
	public int compareTo(Account other) {
		return super.getName().compareTo(other.getName());
	}
}