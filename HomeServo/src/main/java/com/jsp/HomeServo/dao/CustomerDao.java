package com.jsp.HomeServo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServo.dto.Customer;
import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.repository.CustomerRepo;

@Repository
public class CustomerDao {
	@Autowired
	private CustomerRepo repo;
	
	@Autowired
	private WorkDao dao;
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer updateCustomer(Customer customer) {
		Customer c=repo.findById(customer.getId()).get();
		
		if(c!=null) {
			if(customer.getName()==null) {
				customer.setName(c.getName());

			}
			if(customer.getPhone()==0) {
				customer.setPhone(c.getPhone());
			}
			if(customer.getFamilyCount()==0) {
				customer.setFamilyCount(c.getFamilyCount());
			}
			
			if(customer.getEmail()==null) {
				customer.setEmail(c.getEmail());
			}
			
			if(customer.getPwd()==null) {
				customer.setPwd(c.getPwd());
			}
			if(customer.getWorks()==null) {
				customer.setWorks(c.getWorks());
			}
			if(customer.getAddress()==null) {
				customer.setAddress(c.getAddress());
			}
			 repo.save(customer);
			 
			 return customer;
		}
		else {
			return null;
		}
	}
	
	public List<Customer> getAllCustomer(){
		return repo.findAll();
	}
	public Customer getCustomerById(int id) {
		if( repo.findById(id).isPresent()) {
			Customer customer=repo.findById(id).get();
			return customer;
		}
		else {
			return null;
		}
		 
	}
	
	public Customer deleteCustomer(int id) {
		if (repo.findById(id).isPresent()) {
			Customer c1 = repo.findById(id).get();
			
			List<Work> list = dao.getAllWork();
			if (list != null) {
				for (Work work : list) {
					Customer customer=work.getCustomer();
					if ( customer!=null &&   work.getCustomer().getId() == id) {
						work.setCustomer(null);
						dao.updateWork(work);
					}
				}
			}

			repo.deleteById(id);
			return c1;
			}
			else {
				return null;
			}
		}
			
	
	
	
	public Customer getCustomerByEmail(String email) {
		Customer customer= repo.findByEmail(email);
		if(customer!=null) {
			return customer;
		}
		else {
			return null;
		}
		
	}
	
	public Customer deleteCustomerByEmail(String email) {
		Customer customer=repo.findByEmail(email);
		if(customer!=null) {
			 repo.delete(customer);
		}
	return null;
	}
}
