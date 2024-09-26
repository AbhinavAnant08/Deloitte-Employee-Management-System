package com.deloitte.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.model.Employee;

public class DepartmentRepository {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");

    public Department addDepartment(Department department) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(department);
            System.out.println(department);
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
        return department;
    }

    public List<Department> getAllDepartment() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Department> departments = entityManager
                .createQuery("SELECT d FROM Department d", Department.class)
                .getResultList();
        entityManager.close();
        return departments;
    }

    public List<Department> getDepartmentById(int Id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Department> departments = entityManager
                .createQuery("SELECT d FROM Department d where d.id =:DepartmentId", Department.class)
                .setParameter("DepartmentId", Id)
                .getResultList();
        entityManager.close();
        return departments;
    }

    public Department updateDepartment(int id, Department department) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Department existingDepartment = entityManager.find(Department.class, id);
            if (existingDepartment != null) {
                department.setId(id);
                existingDepartment.setId(id);
                existingDepartment.setName(department.getName());
                existingDepartment.setLocation(department.getLocation());
                // Update other fields as needed
                entityManager.merge(existingDepartment);
                transaction.commit();
            } else {
                System.out.println("Department not found.");
                return null;
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
        return department;
    }

    public boolean deleteDepartmentById(int Id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Department department = entityManager.find(Department.class, Id);

        transaction.begin();
        if (department != null) {
            entityManager.remove(department);
            transaction.commit();
            return true;
        }
        return false;
    }
}
