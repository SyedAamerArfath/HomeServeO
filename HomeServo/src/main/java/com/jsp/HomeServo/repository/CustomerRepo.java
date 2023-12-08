package com.jsp.HomeServo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServo.dto.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
//	@Query("select a from Customer a where a.email=?1")
//	if we are maintaining convention like findbyid or findByEmail or findByPhone then no need to add 
//	@query because jpa automatically undeestands that we are fetching based on email or phone.
	public Customer findByEmail(String email);
}
