package com.jsp.HomeServo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServo.dto.Work;

public interface WorkRepo extends JpaRepository<Work, Integer> {
	
	@Query("select a from Work a where a.startDate=null")
	public List<Work> listOfWork();
	
	@Query("select a from Work a where a.startDate is not null and a.endDate is null")
	public List<Work> ongoingWorks();
	
	@Query("select a from Work a where a.startDate is not null and a.endDate is not null")
	public List<Work> completedWorks();

}
