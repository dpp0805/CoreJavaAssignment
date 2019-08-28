package com.greatlearning.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class Distributor implements Runnable {
	private static int idCounter = 100;
	
	private int id;
	private String name;
	private final BlockingQueue<Product> inventory;
	
	public Distributor(String name, BlockingQueue<Product> inventory) {
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
			produce();
		}
	}
	
	private void produce() {
		try {
			Product product = getProduct();
			inventory.put(product);
			System.out.println("Adding product: " + product);
			Thread.sleep(5000);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	private Product getProduct() {
		List<Product> products = new ArrayList<>();
		
		Product p1 = new Product("Samsung TV1", 45000, "LED TV, 52 inch");
		Product p2 = new Product("Samsung TV2", 45000, "LED TV, 52 inch");
		Product p3 = new Product("Samsung TV3", 45000, "LED TV, 52 inch");
		Product p4 = new Product("Samsung TV4", 45000, "LED TV, 52 inch");
		Product p5 = new Product("Samsung TV5", 45000, "LED TV, 52 inch");
		Product p6 = new Product("Samsung TV6", 45000, "LED TV, 52 inch");
		Product p7 = new Product("Samsung TV7", 45000, "LED TV, 52 inch");
		Product p8 = new Product("Samsung TV8", 45000, "LED TV, 52 inch");
		Product p9 = new Product("Samsung TV9", 45000, "LED TV, 52 inch");
		Product p10 = new Product("Samsung TV10", 45000, "LED TV, 52 inch");
		
		products.addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
		
		if(index > 9)
			index = 0;
		
		return products.get(index++);
	}
	private int index = 0;
}
