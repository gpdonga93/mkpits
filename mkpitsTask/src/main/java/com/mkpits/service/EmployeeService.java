package com.mkpits.service;

import java.util.List;
import java.util.Optional;

import com.mkpits.model.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee employee);

	List<Employee> getAllEmployeesSortedAndFiltered(String name);

	Optional<Employee> findById(Long employeeId);

	void delete(Employee employee);

}
