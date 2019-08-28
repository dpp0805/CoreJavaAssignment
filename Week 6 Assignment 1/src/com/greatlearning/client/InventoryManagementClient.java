package com.greatlearning.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.greatlearning.model.Distributor;
import com.greatlearning.model.Product;
import com.greatlearning.model.Retailer;

public class InventoryManagementClient {
	public static void main(String args[]) throws InterruptedException {
		System.out.println("***** Inventory Management starts here *****");
		
		BlockingQueue<Product> inventory = new ArrayBlockingQueue<>(10);
		Distributor distributor = new Distributor("PB Distributors", inventory);
		Retailer retailer = new Retailer("RS Retailers", inventory);
		
		Thread t1 = new Thread(distributor);
		Thread t2 = new Thread(retailer);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("***** Inventory Management ends here *****");
	}
}
