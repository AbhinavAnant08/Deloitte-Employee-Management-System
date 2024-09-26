package com.deloitte.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.deloitte.demo.model.Employee;

public class EmployeeRepository {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");

	public Employee addEmployee(Employee employee) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(employee);
			System.out.println(employee);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		return employee;
	}

	public List<Employee> getAllEmployees() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Employee> employee = entityManager
				.createQuery("SELECT e FROM Employee e", Employee.class)
				.getResultList();
		entityManager.close();
		return employee;
	}

	public List<Employee> getEmployeeById(int Id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Employee> employee = entityManager
				.createQuery("SELECT e FROM Employee e where e.id =:EmployeeId", Employee.class)
				.setParameter("EmployeeId", Id)
				.getResultList();
		entityManager.close();
		return employee;
	}

	public Employee updateEmployee(int id, Employee employeeDetails) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Employee existingEmployee = entityManager.find(Employee.class, id);
			if (existingEmployee != null) {
				employeeDetails.setId(id);
				existingEmployee.setName(employeeDetails.getName());
				existingEmployee.setSalary(employeeDetails.getSalary());
				// Update other fields as needed
				entityManager.merge(existingEmployee);
				transaction.commit();
			} else {
				System.out.println("Employee not found.");
				return null; // Return null if employee not found
			}
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		return employeeDetails; // Return the updated employee details
	}

	public void deleteEmployee(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Employee employee = entityManager.find(Employee.class, id);
			if (employee != null) {
				entityManager.remove(employee);
				System.out.println("Deleted Employee: " + employee);
			} else {
				System.out.println("Employee not found with ID: " + id);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public boolean deleteEmployeeById(int Id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Employee employee = entityManager.find(Employee.class, Id);

		transaction.begin();
		if (employee != null) {
			entityManager.remove(employee);
			transaction.commit();
			return true;
		}
		return false;
	}
}
