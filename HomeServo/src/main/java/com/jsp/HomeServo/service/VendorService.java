package com.jsp.HomeServo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServo.dao.CustomerDao;
import com.jsp.HomeServo.dao.VendorDao;
import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.dto.Vendor;
import com.jsp.HomeServo.exception.EmailNotFoundForCustomerException;
import com.jsp.HomeServo.exception.EmailNotFoundForVendorException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServo.exception.PasswordNotFoundForCustomerException;
import com.jsp.HomeServo.exception.PasswordNotFoundForVendorException;
import com.jsp.HomeServo.util.ResponseStructure;

@Service
public class VendorService {
	@Autowired
	private VendorDao dao;
	
	@Autowired
	private CustomerDao customerDao;
	
	public ResponseEntity<ResponseStructure<Vendor>> saveVendor(Vendor vendor){
		ResponseStructure<Vendor> structure=new ResponseStructure<>();
		structure.setData(dao.saveVendor(vendor));
		structure.setMessage("vendor data  saved successfully ");
		structure.setStatus(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Vendor>> updateVendor(Vendor vendor){
		ResponseStructure<Vendor> structure=new ResponseStructure<>();
		Vendor v=dao.getVendorById(vendor.getId());
		if(v!=null) {
		structure.setData(dao.updateVendor(vendor));
		structure.setMessage("vendor data  updated successfully ");
		structure.setStatus(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new NoSuchElementFoundByVendorException("customer not exist for id "+vendor.getId()+" to  update");
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Vendor>> getVendorById(int id){
		
		ResponseStructure<Vendor> structure=new ResponseStructure<>();
		Vendor vendor=dao.getVendorById(id);
		if(vendor!=null) {
			
		
		structure.setData(vendor);
		structure.setMessage("vendor data  fetched successfully ");
		structure.setStatus(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.FOUND);
		}else
		{
			throw new NoSuchElementFoundByVendorException("no object found for the id "+id+" provided");
		}
	}
	
	public ResponseEntity<ResponseStructure<Vendor>> login(String email,String pwd){
		Vendor vendor=dao.getVendorByEmail(email);
		if(vendor!=null) {
			
			if(vendor.getPwd().equals(pwd)) {
				ResponseStructure<Vendor> structure=new ResponseStructure<>();
				structure.setMessage("login is successfull");
				structure.setData(vendor);
				structure.setStatus(HttpStatus.FOUND.value());
				
				return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.FOUND);
			}
			else 
				throw new PasswordNotFoundForVendorException("invalid password please check your password again");
		}
		else
			throw new EmailNotFoundForVendorException("Invalid Email please check your email once again");
	}
	
	public ResponseEntity<ResponseStructure<Vendor>> deleteVendor(int id){
		ResponseStructure<Vendor> structure=new ResponseStructure<>();
		Vendor vendor=dao.getVendorById(id);
		if(vendor!=null) {
		structure.setMessage("data deleted succefully");
		structure.setData(dao.deleteVendor(id));
		structure.setStatus(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new NoSuchElementFoundByVendorException("no object found to be deleted for id "+id+" provided");
		}
							
	}
	
	public ResponseEntity<ResponseStructure<List<Vendor>>> getAllVendor( int c_id){
		
		Customer customer=customerDao.getCustomerById(c_id);
		if(customer!=null) {
		
		ResponseStructure<List<Vendor>> structure=new ResponseStructure<>();
		List<Vendor> vendor=dao.getAllVendor();
		if(vendor!=null) {
		structure.setMessage("All vendor details fetched succesfully");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(vendor);
		
		return new ResponseEntity<ResponseStructure<List<Vendor>>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new NoSuchElementFoundByVendorException("No  objects found for vendor ");
		}
		}
		else
			throw new NoSuchElementFoundByCustomerException("no customer found based on id");
	}
}
