package com.jsp.HomeServo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.repository.WorkRepo;

@Repository
public class WorkDao {
	@Autowired
	WorkRepo repo;
	
	public Work saveWork(Work work) {
		return repo.save(work);
	}
	
	public Work getWorkById(int id) {
		
		if(repo.findById(id).isPresent()) {
			Work work=repo.findById(id).get();
			return work;
		}
		else {
			return null;
		}
	}
	
	
	public List<Work> listOfWork(){
		List<Work> work=repo.listOfWork();
		if(work!=null) {
			return work;
		}
		else {
			return null;
		}
	}
	
	public Work updateWork(Work work) {
		Work db=repo.findById(work.getId()).get();
		if(db!=null) {
			if(work.getType()==null) {
				work.setType(db.getType());
			}
			
			if(work.getAddress()==null) {
				work.setAddress(db.getAddress());
			}
			if(work.getVendor()==null) {
				work.setVendor(db.getVendor());
			}
			if(work.getCost()==null) {
				work.setCost(db.getCost());
			}
			if(work.getCustomer()==null) {
				work.setCustomer(db.getCustomer());
			}
			if(work.getStartDate()==null) {
				work.setStartDate(db.getStartDate());
			}
			if(work.getEndDate()==null) {
				work.setEndDate(db.getEndDate());
			}
			
			repo.save(work);
			return work;
			
		}
		else {
			return null;
		}
		
	}
	
	public List<Work> getAllWork(){
	return	repo.findAll();
	}
	
	public List<Work> ongoingWorks(){
		return repo.ongoingWorks();
	}
	
	public List<Work> completedWorks(){
		return repo.completedWorks();
	}
	
}
