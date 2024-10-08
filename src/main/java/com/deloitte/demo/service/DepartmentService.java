package com.deloitte.demo.service;

import java.util.List;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.repository.DepartmentRepository;

public class DepartmentService {
    private DepartmentRepository departmentRepository = new DepartmentRepository();

    public Department addDepartment(Department department) {
        System.out.println(department.toString());
        return departmentRepository.addDepartment(department);
    }

    public Department updateDepartment(int id, Department department) {
        System.out.println(department.toString());
        return departmentRepository.updateDepartment(id, department);
    }

    // View all department
    public List<Department> getAllDepartment() {
        return departmentRepository.getAllDepartment();
    }

    public List<Department> getDepartmentById(int Id) {
        return departmentRepository.getDepartmentById(Id);
    }

    public boolean deleteDepartmentById(int Id) {
        return departmentRepository.deleteDepartmentById(Id);
    }
}
