package com.bway.springproject.dao;

import java.util.List;

import com.bway.springproject.model.Employee;

public interface EmployeeDao {
	
	void addEmployee(Employee e);
	void deleteEmployee(int id);
	void updateEmployee(Employee e);
	Employee getById(int id);
	List<Employee> getAll();
}
