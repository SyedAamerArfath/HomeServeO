package com.jsp.HomeServo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.jsp.HomeServo.dao.CustomerDao;
import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.duplicate.CustomerDuplicate;
import com.jsp.HomeServo.exception.EmailNotFoundForCustomerException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServo.exception.PasswordNotFoundForCustomerException;
import com.jsp.HomeServo.util.ResponseStructure;






@Service
public class CustomerService {
	@Autowired
	private CustomerDao dao;
	
	@Autowired
	CustomerDuplicate duplicate;
	
	@Autowired
	ModelMapper mapper;
	
	
	
	
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer){
		
		ResponseStructure<Customer> structure=new ResponseStructure<>();
		structure.setData(dao.saveCustomer(customer));
		structure.setMessage("customer data saved successfully ");
		structure.setStatus(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.CREATED);
	}
	
	
	
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int id) {
		Customer customer=dao.getCustomerById(id);
		
		ResponseStructure<Customer> structure=new ResponseStructure<>();
		
		if(customer!=null) {
		structure.setData(customer);
		structure.setMessage("customer data fetch successfully");
		structure.setStatus(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.FOUND);
	}
		else {
	throw new NoSuchElementFoundByCustomerException("customer id is not found for your id"+id+"to display");
	
	}
}
	
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer){
		ResponseStructure<Customer> structure=new ResponseStructure<>();
		Customer c=dao.getCustomerById(customer.getId());
		if(c!=null) {
		structure.setData(dao.updateCustomer(customer));
		structure.setMessage("customer data updated successfully");
		structure.setStatus(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new NoSuchElementFoundByCustomerException("customer is not found for your id "+customer.getId()+" to update");
		}
	}
	
	public ResponseEntity<ResponseStructure<Customer>> login(String email,String pwd){
		Customer customer=dao.getCustomerByEmail(email);
		if(customer!=null) {
			
			if(customer.getPwd().equals(pwd)) {
				ResponseStructure<Customer> structure=new ResponseStructure<>();
				structure.setData(customer);
				structure.setMessage("customer login successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				
				return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.FOUND);
			}
			throw new PasswordNotFoundForCustomerException();
		}
		throw new EmailNotFoundForCustomerException();
	}
	
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(int id){
		
		ResponseStructure<Customer> structure=new ResponseStructure<>();
		Customer c=dao.getCustomerById(id);
		if(c!=null) {
		structure.setMessage("data deleted Successfully");
		structure.setData(dao.deleteCustomer(id));
		structure.setStatus(HttpStatus.FOUND.value());
		
		return new  ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new NoSuchElementFoundByCustomerException("no object found to be deleted for id "+id);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
		ResponseStructure<List<Customer>> structure=new ResponseStructure<>();
		List<Customer> customer=dao.getAllCustomer();
		if(customer!=null) {
		structure.setMessage("List Of customers data fetch successfully");
		structure.setData(dao.getAllCustomer());
		structure.setStatus(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<List<Customer>>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new NoSuchElementFoundByCustomerException("No Objects Found for Customer");
		}
	}

	
	public ResponseEntity<ResponseStructure<CustomerDuplicate>> getCustomer(int id){
		Customer customer=dao.getCustomerById(id);
		
		if(customer!=null) {
			
//			duplicate.setId(customer.getId());
//			duplicate.setName(customer.getName());
//			duplicate.setEmail(customer.getEmail());
//			duplicate.setPhone(customer.getPhone());
//			duplicate.setFamilyCount(customer.getFamilyCount());
			
			CustomerDuplicate customerDuplicate=this.mapper.map(customer, CustomerDuplicate.class);
			ResponseStructure<CustomerDuplicate> structure=new ResponseStructure<>();
//			structure.setData(duplicate);
			structure.setData(customerDuplicate);
			structure.setMessage("customer fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<CustomerDuplicate>>(structure,HttpStatus.FOUND);
			
			
			
		}
		else
			throw new NoSuchElementFoundByCustomerException("no customer found please check with valid id");
	}
	
}
