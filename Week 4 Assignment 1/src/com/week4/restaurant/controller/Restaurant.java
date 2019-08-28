package com.week4.restaurant.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.week4.restaurant.model.Dish;
import com.week4.restaurant.model.DishType;

public class Restaurant {

	public static void main(String args[]) {
		Dish dish1 = new Dish("Butter Chicken", DishType.Non_Veg, 180.0, 300);
		Dish dish2 = new Dish("Butter Paneer", DishType.Veg, 150.0, 250);
		Dish dish3 = new Dish("Veg Kadhai", DishType.Veg, 130.0, 200);
		Dish dish4 = new Dish("Chicken Biriyani", DishType.Non_Veg, 220.0, 300);
		
		List<Dish> dishes = new ArrayList<>();
		dishes.add(dish1);
		dishes.add(dish2);
		dishes.add(dish3);
		dishes.add(dish4);
		
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
		sc.close();
		
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
		}
		
		Iterator<Dish> it = dishes.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
