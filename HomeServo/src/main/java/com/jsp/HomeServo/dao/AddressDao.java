package com.jsp.HomeServo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServo.dto.Address;
import com.jsp.HomeServo.repository.AddressRepo;

@Repository
public class AddressDao {
	
	@Autowired
	AddressRepo repo;
	
	public Address updateAddress(Address address) {
		Address db=repo.findById(address.getId()).get();
		if(db!=null) {
			if(address.getD_no()==null) {
				address.setD_no(db.getD_no());
			}
			
			if(address.getDistrict()==null) {
				address.setDistrict(db.getDistrict());
			}
			if(address.getLandmark()==null) {
				address.setLandmark(db.getLandmark());
			}
			if(address.getPinCode()==0) {
				address.setPinCode(db.getPinCode());
			}
			if(address.getState()==null) {
				address.setState(db.getState());
			}
			if(address.getStreet()==null) {
				address.setStreet(db.getStreet());
			}
			repo.save(address);
			return address;
		}
		else
			return null;
	}
	
	public Address getAddressById(int id) {
		Address address=repo.findById(id).get();
		if(address!=null) {
			return address;
		}
		else {
			return null;
		}
	}

}
