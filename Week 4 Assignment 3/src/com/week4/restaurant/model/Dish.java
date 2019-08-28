package com.week4.restaurant.model;

public class Dish {
	private static int count = 100;
	
	private int id;
	private String name;
	private DishType dishType;
	private double calorie;
	private double price;
	
	public Dish(String name, DishType dishType, double calorie, double price) {
		this.id = count++;
		
		this.name = name;
		this.dishType = dishType;
		this.calorie = calorie;
		this.price = price;
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

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public DishType getDishType() {
		return dishType;
	}

	public double getCalorie() {
		return calorie;
	}	

	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", dishType=" + dishType + ", calorie=" + calorie + ", price="
				+ price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(calorie);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dishType == null) ? 0 : dishType.hashCode());
		result = prime * result + id;
		result = prime * result + (int) (temp ^ (temp >>> 32));
		
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
		Dish other = (Dish) obj;
		if (Double.doubleToLongBits(calorie) != Double.doubleToLongBits(other.calorie))
			return false;
		if (dishType != other.dishType)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		
		return true;
	}
}
