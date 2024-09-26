package com.deloitte.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "salary")
	private double salary;

	@ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
	
	public Employee(String name, double salary, @NotNull Department department) {
		this.name = name;
		this.salary = salary;
		this.department = department;
	}

	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
