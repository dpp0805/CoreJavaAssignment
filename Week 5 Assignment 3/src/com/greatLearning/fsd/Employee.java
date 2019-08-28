package com.greatLearning.fsd;
import java.util.Objects;

public class Employee implements Comparable<Employee> {
	private static int idCounter = 100;
	
	private final int id;
	private String name;
	private int age;
	private Department department;
	private double salary;
	
	public Employee (String name, int age, double salary) {
		this.id = idCounter++;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Employee (String name, int age, double salary, Department department) {
		this.id = idCounter++;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.department = department;
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

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public int compareTo(Employee other) {
		return this.name.compareTo(other.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", department=" + department + ", salary="
				+ salary + "]\n";
	}
}
