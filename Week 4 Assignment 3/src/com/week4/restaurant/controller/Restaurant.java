package com.week4.restaurant.controller;

import java.util.*;

import com.greatlearning.fsd.module4.exceptions.InvalidAmountException;
import com.week4.restaurant.model.*;

public class Restaurant {

	public static void main(String args[]) {
		Restaurant restaurant = new Restaurant();
		Scanner sc = new Scanner(System.in);
		
		Dish dish1 = new Dish("Butter Chicken", DishType.Non_Veg, 180.0, 300);
		Dish dish2 = new Dish("Butter Paneer", DishType.Veg, 150.0, 250);
		Dish dish3 = new Dish("Veg Kadhai", DishType.Veg, 130.0, 200);
		Dish dish4 = new Dish("Chicken Biriyani", DishType.Non_Veg, 220.0, 300);
		List<Dish> dishes = new ArrayList<>();
		dishes.add(dish1);
		dishes.add(dish2);
		dishes.add(dish3);
		dishes.add(dish4);
		
		//show dishes
		restaurant.showDishes(dishes);
		
		//order dish
		Set<Order> orders = new HashSet<>();
		for(int index = 0; index < 3; ++index) {
			Dish dish = restaurant.orderDish(dishes);
			if(dish == null) continue;
			
			System.out.println("==========You have selected " + dish.getName() + " =====================");
			
			//enter your name
			System.out.println("Please enter your name: ");
			
			String customerName = sc.next();
			
			//print bill
			double billAmount = dish.getPrice();
			System.out.println("Please pay the bill of Rs " + dish.getPrice());
			
			//pay bill
			double amountPaid = sc.nextInt();
			try {
				restaurant.payBill(billAmount, amountPaid);
			}
			catch (InvalidAmountException ex) {
				System.out.println("========Amount paid is less than Bill Amount ================");
				throw ex;
			}
			
			//generate order
			Order order = new Order(dish, customerName);
			orders.add(order);
		}
		
		//show all orders
		System.out.println("================= For Admin =====================");
		System.out.println("5 => to List all the Orders ");
		System.out.println("==========================n=====================");
		
		int option = sc.nextInt();
		switch(option) {
			case 5:
				restaurant.showAllOrders(orders);
				break;
			default:
				System.out.println("Invalid option !!!");
		}	

		sc.close();
	}

	
	private void showDishes(List<Dish> dishes) {
		System.out.println("=================Welcome=====================");
		System.out.println("Please choose from the below options ");
		System.out.println();
		System.out.println("1 => To sort the dishes in the increasing order of calories");
		System.out.println("2 => To sort the dishes in the decreasing order of calories");
		System.out.println("3 => To sort the dishes in the increasing order of price");
		System.out.println("4 => To sort the dishes in the decreasing order of price");
		System.out.println("===============================================");
		Comparator<Dish> calorieAscComparator = (d1, d2) -> Double.valueOf(d1.getCalorie()).compareTo(d2.getCalorie()); 
		Comparator<Dish> calorieDscComparator = (d1, d2) -> Double.valueOf(d2.getCalorie()).compareTo(d1.getCalorie());
		Comparator<Dish> priceAscComparator = (d1, d2) -> Double.valueOf(d1.getPrice()).compareTo(d2.getPrice());
		Comparator<Dish> priceDscComparator = (d1, d2) -> Double.valueOf(d2.getPrice()).compareTo(d1.getPrice());
		
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch(option) {
			case 1:
				dishes.sort(calorieAscComparator);
				break;
			case 2:
				dishes.sort(calorieDscComparator);
				break;
			case 3:
				dishes.sort(priceAscComparator);
				break;
			case 4:
				dishes.sort(priceDscComparator);
				break;
			default: 
				System.out.println("Cannot sort. Invalid option !!!");
		}
	}
	
	private Dish orderDish(List<Dish> dishes) {
	    System.out.println("=================Please select the Dish Id and hit enter===============================");	
		Iterator<Dish> it = dishes.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("=======================================================================================");
		
		Scanner sc = new Scanner(System.in);
		int orderId = sc.nextInt();
		
		Dish selectedDish = null;
		Iterator<Dish> it2 = dishes.iterator();
		while(it2.hasNext()) {
			Dish dish = it2.next();
			if(dish.getId() == orderId) {
				selectedDish = dish;
				break;
			}
		}
		
		if(selectedDish == null)
			System.out.println("Invalid Dish selected !!!");
		
		return selectedDish;
	}
	
	private void payBill(double billAmount, double amountPaid) {
		if (amountPaid > billAmount) {
			double change = amountPaid - billAmount;
			System.out.println("========Thanks for your order. Please collect the change = Rs " + change +" ================");
		}
		else if (amountPaid == billAmount) {
			System.out.println("========Thanks for your order. ================");
		}
		else {
			throw new InvalidAmountException(" Amount paid is less than bill amount.");
		}
	}
	
	private void showAllOrders(Set<Order> orders) {
		Iterator<Order> it = orders.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
