package com.deloitte.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.model.Employee;

public class EmployeeRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");

    public boolean addEmployee(Employee employee) {
        boolean isAdded = false;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(employee);
            System.out.println(employee);
            transaction.commit();
            isAdded = true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            entityManager.close();
        }
        return isAdded;
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

    public boolean updateEmployee(int id, Employee employeeDetails) {
        boolean isUpdated = false;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Employee existingEmployee = entityManager.find(Employee.class, id);
            if (existingEmployee != null) {
                employeeDetails.setId(id);
                existingEmployee.setName(employeeDetails.getName());
                existingEmployee.setSalary(employeeDetails.getSalary());
                if (employeeDetails.getDepartment() != null) {
                    Department newDepartment = entityManager.find(Department.class, employeeDetails.getDepartment().getId());
                    existingEmployee.setDepartment(newDepartment);
                }
                // Update other fields as needed
                entityManager.merge(existingEmployee);
                transaction.commit();
                isUpdated = true;
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
        return isUpdated; // Return the updated employee details
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
