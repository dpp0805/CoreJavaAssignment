package com.greatlearning.model;

import java.util.concurrent.BlockingQueue;

public class Retailer implements Runnable {
	private static int idCounter = 200;
	
	private int id;
	private String name;
	private final BlockingQueue<Product> inventory;
	
	public Retailer(String name, BlockingQueue<Product> inventory) {
		this.id = idCounter++;
		this.name = name;
		this.inventory = inventory;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public BlockingQueue<Product> getInventory() {
		return inventory;
	}
	
	@Override
	public void run() {
		while(true) {
			sell();
		}
	}
	
	private void sell() {
		try {
			Product product = inventory.take();
			System.out.println("Selling product: " + product);
			Thread.sleep(8000);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
