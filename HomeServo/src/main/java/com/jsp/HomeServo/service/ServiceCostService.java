package com.jsp.HomeServo.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServo.dao.CustomerDao;
import com.jsp.HomeServo.dao.ServiceCostDao;
import com.jsp.HomeServo.dao.VendorDao;
import com.jsp.HomeServo.dao.WorkDao;
import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.dto.ServiceCost;
import com.jsp.HomeServo.dto.Vendor;
import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.exception.NoSuchElementFoundByCustomerException;
import com.jsp.HomeServo.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServo.exception.NoSuchElementFoundForCostException;
import com.jsp.HomeServo.exception.NoSuchElementFoundForWork;
import com.jsp.HomeServo.util.ResponseStructure;

@Service
public class ServiceCostService {
	
	@Autowired
	VendorDao vendorDao;
	
	@Autowired
	WorkDao workDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	ServiceCostDao costDao;
	
//	@Autowired
//	ServiceCost cost;
	
	public ResponseEntity<ResponseStructure<ServiceCost>> saveCost(int w_id,int v_id){
		Vendor vendor=vendorDao.getVendorById(v_id);
		if(vendor!=null) {
			Work work=workDao.getWorkById(w_id);
			if(work!=null) {
				double costPerDay=vendor.getCostPerDay();
				
				Date start=work.getStartDate();
				Date end=work.getEndDate();
				
				Duration duration=Duration.between(start.toInstant(), end.toInstant());
				int days=(int) duration.toDays();
				
				ServiceCost cost=new ServiceCost();
				
				cost.setDays(days);
				cost.setTotalAmount(days*costPerDay);
				
				ServiceCost cost2=costDao.saveCost(cost);
				List<ServiceCost> list=new ArrayList<ServiceCost>();
				list.add(cost2);
				list.addAll(vendor.getCosts());
				
				
				
				vendor.setCosts(list);
				vendorDao.updateVendor(vendor);
				work.setCost(cost2);
				workDao.updateWork(work);
				
				ResponseStructure<ServiceCost> structure=new ResponseStructure<ServiceCost>();
				structure.setData(cost2);
				structure.setMessage("cost saved successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				
				return new ResponseEntity<ResponseStructure<ServiceCost>>(structure,HttpStatus.CREATED);
				
				
			}
			else
				throw new NoSuchElementFoundForWork("no work found based on vendor");
		}
		else
			throw new NoSuchElementFoundByVendorException("no vendor found based on id provided");
	}
	
	public ResponseEntity<ResponseStructure<ServiceCost>> payment(int c_id, ServiceCost cost){
		Customer customer=customerDao.getCustomerById(c_id);
		if(customer!=null) {
			ServiceCost cost2=costDao.getServiceCost(cost.getId());
			
			if(cost2!=null) {
				ResponseStructure<ServiceCost> structure=new ResponseStructure<>();
				structure.setData(costDao.payServiceCost(cost));
				structure.setMessage("payment saved succefully");
				structure.setStatus(HttpStatus.FOUND.value());
				
				return new ResponseEntity<ResponseStructure<ServiceCost>>(structure,HttpStatus.FOUND);
			}
			else
				throw new NoSuchElementFoundForCostException("no cost present right now");
		}
		else
			throw new NoSuchElementFoundByCustomerException("No Customer found ");
	}

}
