package com.jsp.HomeServo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServo.dto.ServiceCost;
import com.jsp.HomeServo.dto.Vendor;
import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.repository.VendorRepo;

@Repository
public class VendorDao  {
	@Autowired
	private VendorRepo repo;
	
	@Autowired
	private WorkDao workDao;
	
	public Vendor saveVendor(Vendor vendor) {
		return repo.save(vendor);
	}
	
	public List<Vendor> getAllVendor(){
		return repo.findAll();
	}
	
	public Vendor getVendorById(int id) {
		
		if(repo.findById(id).isPresent()) {
			Vendor vendor=repo.findById(id).get();
			return vendor;
		}
		else {
			return null;
		}
	}
	
	public Vendor updateVendor(Vendor vendor) {
		Vendor v= repo.findById(vendor.getId()).get();
		if(v!=null) {
			if(vendor.getName()==null) {
				vendor.setName(v.getName());
			}
			if(vendor.getPhone()==0) {
				vendor.setPhone(v.getPhone());
			}
			if(vendor.getEmail()==null) {
				vendor.setEmail(v.getEmail());
			}
			if(vendor.getPwd()==null) {
				vendor.setPwd(v.getPwd());
			}
			if(vendor.getRole()==null) {
				vendor.setRole(v.getRole());
			}
			if(vendor.getCostPerDay()==0) {
				vendor.setCostPerDay(v.getCostPerDay());
			}
			if(vendor.getAddress()==null) {
				vendor.setAddress(v.getAddress());
			}
			if(vendor.getCosts()==null) {
				vendor.setCosts(v.getCosts());
			}
			repo.save(vendor);
			return vendor;
		}
		else {
			return null;
		}
	}
	
	public Vendor deleteVendor(int id) {
		
		
		
		if (repo.findById(id).isPresent()) {
			Vendor v1 = repo.findById(id).get();

			List<Work> list = workDao.getAllWork();
			if (list != null) {
				List<Vendor> updatedVendor = new ArrayList<>();

				for (Work work : list) {
					List<Vendor> ven = work.getVendor();
					if (ven != null) {
						for (Vendor vendor : ven) {

							if (vendor.getId() != id) {
								updatedVendor.add(vendor);

							}
						}
					}

					work.setVendor(updatedVendor);
					workDao.updateWork(work);

				}
			}

			v1.setCosts(null);
			repo.deleteById(id);
			return v1;
		} else
			return null;
		
		
	}
	
	public Vendor getVendorByEmail(String email) {
		Vendor vendor= repo.findByEmail(email);
		if(vendor!=null) {
			return vendor;
		}
		else {
			return null;
		}
	}
	
	public Vendor deleteVendorByEmail(String email) {
		Vendor vendor=repo.findByEmail(email);
		if(vendor!=null) {
			repo.delete(vendor);
		}
		return null;
	
	}

}
