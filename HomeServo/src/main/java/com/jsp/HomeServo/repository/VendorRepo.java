package com.jsp.HomeServo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServo.dto.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, Integer> {
	
//	@Query("select a from Vendor a where a.email=?1")
	public Vendor findByEmail(String email);
}
