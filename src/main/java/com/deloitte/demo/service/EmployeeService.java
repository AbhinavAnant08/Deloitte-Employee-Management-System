package com.deloitte.demo.service;

import java.util.List;
import com.deloitte.demo.model.Employee;
import com.deloitte.demo.repository.EmployeeRepository;

public class EmployeeService {

	private EmployeeRepository employeeRepository = new EmployeeRepository();

	// Add a new employee with fields: id, name, salary, and department.
	public Employee addEmployee(Employee employee) {
		System.out.println(employee.toString());
		return employeeRepository.addEmployee(employee);
	}

	public Employee updateEmployee(int id, Employee employeeDetails) {
        System.out.println(employeeDetails.toString());
        return employeeRepository.updateEmployee(id, employeeDetails);
    }

	// View all employees.
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployees();
	}

	public List<Employee> getEmployeeById(int Id){
		return employeeRepository.getEmployeeById(Id);
	}

	public boolean deleteEmployeeById(int Id) {
		return employeeRepository.deleteEmployeeById(Id);
	}
	
	
	// Fetch an employee by id.
	// Update employee information.
	// Delete an employee.


}