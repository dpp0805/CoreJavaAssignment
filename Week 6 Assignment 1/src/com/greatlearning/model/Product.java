package com.greatlearning.model;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Comparable<Product>, Serializable {
	private static final long serialVersionUID = 4627295451926714771L;
	private static int idCounter = 1;
	
	private int id;
	private String name;
	private double price;
	private String desc;
	
	public Product(String name, double price, String desc) {
		this.id = idCounter++;
		this.name = name;
		this.price = price;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int compareTo(Product other) {
		return this.name.compareTo(other.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", desc=" + desc + "]";
	}
}
