package com.greatLearning.fsd;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Client {	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) {
		List<Employee> employees = new ArrayList<>();	
		populateData(employees);
		System.out.println("*************** Employees *****************");
		System.out.println(employees);
		
		System.out.println("*************** Filter ********************");
		System.out.println("Filter By- ");
		System.out.println("Press 1 => Filter the employees having age less than the given input value");
		System.out.println("Press 2 => Filter all the employees having age more than given input value");
		System.out.println("Press 3 => Filter all the employees having salary more than the given value and belong to the given department");
		System.out.println("Press 4 => Print the name of the employees whose salary is greater than the input value and belong to the given department");
		
		List<Employee> filteredEmployees = new ArrayList<>();		
		int option = sc.nextInt();
		switch(option) {
			case 1:
				System.out.println("Enter age: ");
				int filterByAge = sc.nextInt();
				filteredEmployees.addAll(filterEmployees(employees, emp -> emp.getAge() < filterByAge));
				System.out.println(filteredEmployees);
				break;
			case 2:
				System.out.println("Enter age: ");
				filterByAge = sc.nextInt();
				filteredEmployees.addAll(filterEmployees(employees, emp -> emp.getAge() > filterByAge));
				System.out.println(filteredEmployees);
				break;
			case 3:
				System.out.println("Enter age: ");
				filterByAge = sc.nextInt();
				System.out.println("Enter department:");
				String filterByDepartment = sc.next();
				filteredEmployees.addAll(filterEmployees(employees, emp -> emp.getAge() > filterByAge 
																		   && emp.getDepartment() != null
																		   && emp.getDepartment().toString().equalsIgnoreCase(filterByDepartment)));
				System.out.println(filteredEmployees);
				break;
			case 4:
				System.out.println("Enter age: ");
				filterByAge = sc.nextInt();
				System.out.println("Enter department:");
				filterByDepartment = sc.next();
				employees.stream()
						 .filter(emp -> emp.getAge() > filterByAge 
								 		&& emp.getDepartment() != null
								 		&& emp.getDepartment().toString().equalsIgnoreCase(filterByDepartment))
						 .forEach(System.out :: println);
				break;
			default:
				System.out.println("Invalid Option !!!");	
		}
		
		sc.close();
	}

	private static List<Employee> filterEmployees(List<Employee> employees, Predicate<Employee> filterCriteria) {
		List<Employee> filteredEmployees = new ArrayList<>();		
		Iterator<Employee> it = employees.iterator();
		while(it.hasNext()) {
			Employee emp = it.next();
			if(filterCriteria.test(emp) == true)
				filteredEmployees.add(emp);
		}
		
		return filteredEmployees;
	}
	
	private static void populateData(List<Employee> employees) {
		Employee emp1 = new Employee("Preetom", 25, 30000);
		Employee emp2 = new Employee("Rohit", 29, 45000, Department.FINANCE);
		Employee emp3 = new Employee("Kohli", 30, 50000, Department.MARKETING);
		Employee emp4 = new Employee("Dhoni", 35, 80000, Department.HR);
		Employee emp5 = new Employee("Smith", 28, 50000);
		Employee emp6 = new Employee("Carey", 25, 40000, Department.SALES);
		Employee emp7 = new Employee("Starc", 29, 50000, Department.MARKETING);
		Employee emp8 = new Employee("Roy", 26, 40000, Department.SALES);
		Employee emp9 = new Employee("Williamson", 30, 40000, Department.MARKETING);
		Employee emp10 = new Employee("Pant", 21, 40000);
		
		employees.addAll(Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9, emp10));
	}	
}
