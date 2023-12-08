package com.jsp.HomeServo.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServo.dao.CustomerDao;
import com.jsp.HomeServo.dao.VendorDao;
import com.jsp.HomeServo.dao.WorkDao;
import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.dto.Vendor;
import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServo.exception.NoSuchElementFoundForWork;
import com.jsp.HomeServo.util.ResponseStructure;

@Service
public class WorkService {
	
	@Autowired
	private WorkDao dao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private VendorDao vendorDao;
	
	public ResponseEntity<ResponseStructure<Work>> saveWork(Work work,int cus_id){
		Customer customer=customerDao.getCustomerById(cus_id);
		if(customer!=null) {
			work.setCustomer(customer);
			ResponseStructure<Work> structure=new ResponseStructure<>();
			structure.setData(dao.saveWork(work));
			structure.setMessage("work saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			
			return new ResponseEntity<ResponseStructure<Work>>(structure,HttpStatus.CREATED);
		}
		else
			throw new NoSuchElementFoundByCustomerException("no customer found by provided id");
	}
	
	public ResponseEntity<ResponseStructure<Work>> getWorkById(int id){
		Work work=dao.getWorkById(id);
		if(work!=null) {
			ResponseStructure<Work> structure=new ResponseStructure<>();
			structure.setData(work);
			structure.setMessage("work fetched succefully");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Work>>(structure,HttpStatus.FOUND);
		}
		else
			throw new  NoSuchElementFoundForWork("No work found based on id ");
	}
	
	
	public ResponseEntity<ResponseStructure<Work>> startDate(int w_id,int v_id){
		Vendor vendor=vendorDao.getVendorById(v_id);
		if(vendor!=null) {
			Work work=dao.getWorkById(w_id);
			if(work!=null) {
				Date date=new Date(new Date().getTime());
				work.setStartDate(date);
				List<Vendor> list=new ArrayList<>();
				list.add(vendor);
				work.setVendor(list);
				
				ResponseStructure<Work> structure=new ResponseStructure<>();
				structure.setData(dao.updateWork(work));
				structure.setMessage("work updated successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				
				return new ResponseEntity<ResponseStructure<Work>>(structure,HttpStatus.FOUND);
			}
			else
				throw new NoSuchElementFoundForWork("no work found based on id");
		}
		else 
			throw new NoSuchElementFoundByVendorException("no vendor found based on id provided");
			
		
	}
	
	public ResponseEntity<ResponseStructure<Work>> endDate(int w_id,int v_id){
		Vendor vendor=vendorDao.getVendorById(v_id);
		if(vendor!=null) {
			Work work=dao.getWorkById(w_id);
			if(work!=null) {
				Date date=new Date(new Date().getTime());
				work.setEndDate(date);
				ResponseStructure<Work> structure=new ResponseStructure<>();
				structure.setData(dao.updateWork(work));
				structure.setMessage("work updated succefully");
				structure.setStatus(HttpStatus.FOUND.value());
				
				return new ResponseEntity<ResponseStructure<Work>>(structure,HttpStatus.FOUND);
			}
			else
				throw new NoSuchElementFoundForWork("no work found based on your id provided");
		}
		else
			throw new NoSuchElementFoundByVendorException("no vendor found based on your id provided");
	}
	
	public ResponseEntity<ResponseStructure<List<Work>>> listOfWork(int v_id){
		Vendor vendor=vendorDao.getVendorById(v_id);
		if(vendor!=null) {
			ResponseStructure<List<Work>> structure=new ResponseStructure<>();
			structure.setData(dao.listOfWork());
			structure.setMessage("List Of works avaliable for vendor is");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure,HttpStatus.FOUND);
		}
		else
			throw new NoSuchElementFoundByVendorException("no vendor found based on id");
	}
	
	
	public ResponseEntity<ResponseStructure<List<Work>>> ongoingWorks(int v_id){
		Vendor vendor=vendorDao.getVendorById(v_id);
		if(vendor!=null) {
			ResponseStructure<List<Work>> structure=new ResponseStructure<>();
			structure.setData(dao.ongoingWorks());
			structure.setMessage("list of ongoing works are");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure,HttpStatus.FOUND);
		}
		else
			throw new NoSuchElementFoundByVendorException("no vendor found ");
	}
	
	public ResponseEntity<ResponseStructure<List<Work>>> completedWorks( int v_id){
		Vendor vendor=vendorDao.getVendorById(v_id);
		if(vendor!=null) {
			ResponseStructure<List<Work>> structure=new ResponseStructure<>();
			structure.setData(dao.completedWorks());
			structure.setMessage("list of completed works are");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure,HttpStatus.FOUND);
		}
		throw new NoSuchElementFoundByVendorException("no vendor found");
	}
	

}
