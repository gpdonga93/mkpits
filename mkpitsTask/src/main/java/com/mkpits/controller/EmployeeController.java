package com.mkpits.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkpits.model.Address;
import com.mkpits.model.Employee;
import com.mkpits.service.AddressService;
import com.mkpits.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;
	
	@Autowired
	public AddressService addressService;
	
	/**
	 * Create new employee
	 * @param employee
	 * @return
	 */
	@PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {
		 Employee savedEmployee = employeeService.createEmployee(employee);
		 return ResponseEntity.ok().body(savedEmployee);
    }
	
	/**
	 * Add new address for employee
	 * @param employeeId
	 * @param address
	 * @return
	 */
	@PostMapping("/{employeeId}/address")
    public ResponseEntity<Address> createAddressForEmployee(@Valid @PathVariable Long employeeId, 
    															@RequestBody Address address) {
        Optional<Employee> employeeOptional = employeeService.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            address.setEmployee(employee);
            Address savedAddress = addressService.createAddress(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	/**
	 * Update Employee
	 * @param employeeId
	 * @param updatedEmployee
	 * @return
	 */
	@PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable Long employeeId, 
    													  @RequestBody Employee updatedEmployee) {
        Optional<Employee> employeeOptional = employeeService.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setNumber(updatedEmployee.getNumber());
            employee.setSalary(updatedEmployee.getSalary());
            
            Employee savedEmployee = employeeService.createEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	/**
	 * Update address for particular employee
	 * @param employeeId
	 * @param addressId
	 * @param updatedAddress
	 * @return
	 */
	 @PutMapping("/{employeeId}/address/{addressId}")
	    public ResponseEntity<Address> updateEmployeeAddress(@Valid @PathVariable Long employeeId,
	            											 @PathVariable Long addressId,
	            											 @RequestBody Address updatedAddress) {
	        Optional<Employee> employeeOptional = employeeService.findById(employeeId);
	        if (employeeOptional.isPresent()) {
	            Optional<Address> addressOptional = addressService.findById(addressId);
	            if (addressOptional.isPresent()) {
	                Address address = addressOptional.get();
	                address.setCity(updatedAddress.getCity());
	                address.setStreet(updatedAddress.getStreet());
	                address.setState(updatedAddress.getState());
	                address.setLandmark(updatedAddress.getLandmark());
	                
	                Address savedAddress = addressService.createAddress(address);
	                return new ResponseEntity<>(savedAddress, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 /**
	  * Get employee by ID
	  * @param employeeId
	  * @return
	  */
	 @GetMapping("/{employeeId}")
	 public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
	        Optional<Employee> employeeOptional = employeeService.findById(employeeId);
	        return employeeOptional.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
	
	/**
	 * Fetch All Employee (include sort and searching Name)
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "Get all employees", notes = "Returns a list of all employees.")
	@GetMapping
    public List<Employee> getAllEmployees(@RequestParam(required = false) String name) {
		return employeeService.getAllEmployeesSortedAndFiltered(name);
    }
	
	/**
	 * Fetch all addresses of a particular employee
	 * @param employeeId
	 * @return
	 */
	@GetMapping("/{employeeId}/addresses")
    public ResponseEntity<List<Address>> getAllAddressesOfEmployee(@PathVariable Long employeeId) {
        Optional<Employee> employeeOptional = employeeService.findById(employeeId);
        if (employeeOptional.isPresent()) {
            List<Address> addresses = employeeOptional.get().getAddresses();
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	/**
	 * Delete employee by Id
	 * @param employeeId
	 * @return
	 */
	 @DeleteMapping("/{employeeId}")
	    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
	        Optional<Employee> employeeOptional = employeeService.findById(employeeId);
	        if (employeeOptional.isPresent()) {
	            Employee employee = employeeOptional.get();

	            addressService.deleteAll(employee.getAddresses());

	            employeeService.delete(employee);
	            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	        }
	    }
}
