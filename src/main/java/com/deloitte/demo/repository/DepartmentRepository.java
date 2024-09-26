package com.deloitte.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.deloitte.demo.model.Department;

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

}
