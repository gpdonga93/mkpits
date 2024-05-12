package com.mkpits.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkpits.model.Address;
import com.mkpits.model.Employee;
import com.mkpits.repository.EmployeeRepository;
import com.mkpits.service.AddressService;
import com.mkpits.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	public EmployeeRepository employeeRepository;
	
	@Autowired
	public AddressService addressService;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Transactional
	public Employee createEmployee(Employee employee) {
		 for (Address address : employee.getAddresses()) {
	            address.setEmployee(employee);
	            addressService.createAddress(address);
	        }
        return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployeesSortedAndFiltered(String name) {
		 Stream<Employee> employeeStream = employeeRepository.findAll().stream();
	        
	        if (name != null && !name.isEmpty()) {
	            employeeStream = employeeStream.filter(employee -> employee.getName().contains(name));
	        }
	        List<Employee> sortedAndFilteredEmployees = employeeStream
	                .sorted(Comparator.comparing(Employee::getName))
	                .collect(Collectors.toList());
	        return sortedAndFilteredEmployees;
	}

	@Override
	public Optional<Employee> findById(Long employeeId) {
		return employeeRepository.findById(employeeId);
	}

	@Override
	public void delete(Employee employee) {
		 employeeRepository.delete(employee);
	}
 }
