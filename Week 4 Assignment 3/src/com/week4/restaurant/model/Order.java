package com.week4.restaurant.model;

public class Order implements Comparable<Order>{
	private static int counter = 1;
	
	private int orderId;
	private Dish dish;
	private String customerName;
	
	public Order(Dish dish, String customerName) {
		this.dish = dish;
		this.customerName = customerName;
		this.orderId = counter++;
	}

	public int getOrderId() {
		return orderId;
	}

	public Dish getDish() {
		return dish;
	}

	public String getCustomerName() {
		return customerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((dish == null) ? 0 : dish.hashCode());
		result = prime * result + orderId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dish == null) {
			if (other.dish != null)
				return false;
		} else if (!dish.equals(other.dish))
			return false;
		if (orderId != other.orderId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order Id: " + orderId + ", Dish: " + dish.getName() + ", Customer Name: " + customerName;
	}

	@Override
	public int compareTo(Order other) {
		return this.customerName.compareTo(other.getCustomerName());
	}

}
