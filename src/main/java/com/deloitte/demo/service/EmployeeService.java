package com.deloitte.demo.service;

import java.util.List;
import com.deloitte.demo.model.Employee;
import com.deloitte.demo.repository.EmployeeRepository;

public class EmployeeService {

	private EmployeeRepository employeeRepository = new EmployeeRepository();

	public Employee addEmployee(Employee employee) {
		System.out.println(employee.toString());
		return employeeRepository.addEmployee(employee);
	}

	public List<Employee> getAllEmployees() {
//		System.out.println();
		return employeeRepository.getAllEmployees();
	}

}