 package com.jsp.HomeServo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.duplicate.CustomerDuplicate;
import com.jsp.HomeServo.service.CustomerService;
import com.jsp.HomeServo.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CustomerController {
	@Autowired
	private CustomerService service;
	

	@PostMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}
	

	@PutMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@RequestParam int id) {
		return service.getCustomerById(id);
	}
	@GetMapping("/customer/login")
	public ResponseEntity<ResponseStructure<Customer>> login(@RequestParam String email,@RequestParam String pwd){
		return service.login(email, pwd);
	}
	
	@DeleteMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@RequestParam int id){
		return service.deleteCustomer(id);
	}
	
	
//	for getting only customer details and avoiding mapped objects and password
	@GetMapping("/customer/get")
	public ResponseEntity<ResponseStructure<CustomerDuplicate>> getCustomer(@RequestParam int id){
		return service.getCustomer(id);
	}
	
}
