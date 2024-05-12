package com.mkpits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mkpits.service.AddressService;
import com.mkpits.service.EmployeeService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	public AddressService addressService;
	
	@Autowired
	public EmployeeService employeeService;
	
	
}
